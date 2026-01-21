package com.demoproject.accounts;

import com.demoproject.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
/* If all my classes are in different package then we have to
mention specifically the location of components and entities
@ComponentScans({ @ComponentScan("com.demoproject.accounts.controller") })
@EnableJpaRepositories("com.demoproject.accounts.repository")
@EntityScan("com.demoproject.accounts.model")*/
@OpenAPIDefinition(info = @Info(
		title = "Accounts Microservice REST API Documentation",
		description = "EazyBank Accounts Microservice REST API Documentation",
		version = "v1",
		contact = @Contact(
				name = "Moazzam Mahmood",
				email = "moazzam5672@gmail.com"
		),
		license = @License(
				name = "Apache2.0",
				url = "www.testing.com"
		)
     ),
		externalDocs = @ExternalDocumentation(
				description = "EazyBank Accounts Microservice REST API Documentation",
				url = "www.testing.com"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
