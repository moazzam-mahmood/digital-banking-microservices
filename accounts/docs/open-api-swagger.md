# OpenAPI (Swagger) Integration with Spring Boot
## Complete Interview Revision Guide (All-in-One)

---

## 1. What is OpenAPI?

**OpenAPI** is a language-agnostic specification used to describe REST APIs in a standard format.

- Earlier known as **Swagger Specification**
- Defines:
    - API endpoints
    - HTTP methods
    - Request & response schemas
    - Error formats
    - Authentication mechanisms

**Swagger** refers to the tooling ecosystem built on OpenAPI:
- Swagger UI
- Swagger Editor
- Swagger Codegen

---

## 2. Why OpenAPI Is Used (Interview Answer)

OpenAPI is used to:

- Automatically document REST APIs
- Provide interactive API UI
- Act as a contract between backend & frontend
- Help QA, frontend & external teams
- Enable client SDK generation
- Improve maintainability and onboarding

> In microservices architecture, OpenAPI often becomes the **single source of truth**.

---

## 3. Spring Boot + OpenAPI Integration

Spring Boot integrates OpenAPI using **springdoc-openapi**.

### Common Endpoints

| Endpoint | Purpose |
|--------|--------|
| `/v3/api-docs` | Raw OpenAPI JSON |
| `/swagger-ui/index.html` | Swagger UI |

---

## 4. High-Level Internal Flow (Must Know)

1. Spring Boot application starts
2. `springdoc-openapi` scans:
    - `@RestController`
    - `@RequestMapping`
    - OpenAPI annotations
3. Builds OpenAPI model in memory
4. Exposes `/v3/api-docs`
5. Swagger UI consumes JSON and renders documentation

---

## 5. `@OpenAPIDefinition` – Global API Metadata

### Purpose
Defines **application-wide API metadata**.

### Example

```java
@OpenAPIDefinition(
    info = @Info(
        title = "Accounts Microservice REST API Documentation",
        description = "EazyBank Accounts Microservice REST API Documentation",
        version = "v1",
        contact = @Contact(
            name = "Moazzam Mahmood",
            email = "moazzam5672@gmail.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        )
    ),
    externalDocs = @ExternalDocumentation(
        description = "EazyBank Documentation",
        url = "https://www.testing.com"
    )
)


### Interview Points

Only one @OpenAPIDefinition per application

Applied on main class or config class

Appears at the top of Swagger UI

6. @Tag – Grouping APIs
Purpose

Groups related APIs logically in Swagger UI.

@Tag(
    name = "CRUD REST APIs for Accounts in EazyBank",
    description = "CREATE, UPDATE, FETCH AND DELETE account details"
)

Interview Points

Applied at controller level

Improves readability in large applications

7. @Operation – API Method Description
Purpose

Describes what a specific endpoint does.

@Operation(
    summary = "Create Account REST API",
    description = "Creates new Customer and Account inside EazyBank"
)

Interview Points

summary → short heading

description → business explanation

Replaces Swagger 2 @ApiOperation

8. @ApiResponses & @ApiResponse – Response Documentation
Purpose

Documents all possible HTTP responses.

@ApiResponses({
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status CREATED"
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error",
        content = @Content(
            schema = @Schema(implementation = ErrorResponseDto.class)
        )
    )
})

Interview Points

Improves API contract clarity

Helps frontend & QA teams

Essential for error handling documentation

9. @Schema – DTO Documentation
Purpose

Describes request and response DTO fields.

@Schema(description = "Error response")
public class ErrorResponseDto {

    @Schema(
        description = "Error message",
        example = "Internal Server Error"
    )
    private String message;

    @Schema(
        description = "Timestamp of the error"
    )
    private LocalDateTime timestamp;
}

10. Complete End-to-End Controller Example
@RestController
@RequestMapping("/api/accounts")
@Tag(
    name = "CRUD REST APIs for Accounts in EazyBank",
    description = "Account management APIs"
)
public class AccountsController {

    @PostMapping
    @Operation(
        summary = "Create Account REST API",
        description = "Creates new customer and account"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Created"),
        @ApiResponse(
            responseCode = "500",
            description = "Server Error",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
        )
    })
    public ResponseEntity<String> createAccount() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Account created");
    }
}

11. Validation + OpenAPI Integration

Validation annotations are automatically picked up by Swagger.

public class AccountRequest {

    @NotBlank
    @Schema(description = "Customer name")
    private String name;

    @Email
    @Schema(description = "Customer email")
    private String email;
}

12. API Versioning in OpenAPI
URL Versioning
/api/v1/accounts
/api/v2/accounts

OpenAPI Version Field
version = "v1"

13. Securing APIs in Swagger (JWT Example)
Define Security Scheme
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)

Apply Security on Endpoint
@Operation(
    security = @SecurityRequirement(name = "bearerAuth")
)

14. Disable Swagger in Production (Very Important)
springdoc:
  api-docs:
    enabled: false
  swagger-ui:
    enabled: false

Best Practice

Enabled in dev / test

Disabled in production

15. Common Mistakes (Interview Traps)

❌ Incompatible Spring Boot & springdoc versions
❌ Using old Swagger 2 annotations
❌ Not documenting error responses
❌ Exposing Swagger publicly
❌ Over-documenting trivial endpoints

16. Best Practices (Production Ready)

✔ One @OpenAPIDefinition
✔ @Tag at controller level
✔ @Operation on public APIs
✔ Always document error responses
✔ Disable Swagger in prod
✔ Keep descriptions business-oriented

17. Swagger vs OpenAPI (Direct Question)
OpenAPI	Swagger
Specification	Tooling
API contract	UI / Editor
Language agnostic	Implementation
18. Is OpenAPI Mandatory?

❌ No
✔ APIs work without it
✔ Strongly recommended for real projects

19. One-Line Interview Summary

OpenAPI integration in Spring Boot uses springdoc to automatically generate API documentation, enhanced using annotations like @OpenAPIDefinition, @Tag, @Operation, and @ApiResponses to define metadata, grouping, behavior, and response contracts.

20. 30-Second Final Interview Answer

We integrate OpenAPI in Spring Boot using springdoc, which scans controllers and OpenAPI annotations to generate OpenAPI JSON and Swagger UI. Global metadata is defined using @OpenAPIDefinition, APIs are grouped using @Tag, endpoints are described using @Operation, and response contracts are defined using @ApiResponses. Swagger is usually enabled only in non-production environments.