package com.demoproject.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold customer and account information."
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer", example = "Moazzam Mahmood"
    )
    @NotEmpty(message = "Name can't be null or empty")
    @Size(min = 3, max = 20, message = "Length of customer name should be between 3 to 30")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "xyz@gmail.com"
    )
    @NotEmpty(message = "email address can't be null or empty")
    @Email(message = "Email address is invalid")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;


}
