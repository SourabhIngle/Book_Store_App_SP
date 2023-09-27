package com.bridgelabz.bookstoreapp.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Invalid First name or First letter capital")
    @NotEmpty(message = "Enter first name")
    private String firstName;
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Invalid Last name or First letter capital")
    @NotEmpty(message = "Enter last name")
    private String lastName;
//    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = "DateOfBirth should be in the past")
    private LocalDate dateOfBirth;
    @NotEmpty(message = "Enter password")

    private String password;
    @Pattern(regexp = "^[A-Za-z]{3,}(([+]|[-]|[.])?[a-zA-Z0-9]+)?@[a-zA-Z0-9]{1,}+[.][a-zA-Z0-9]{3}([.]|[,])?([a-zA-Z]?)*$", message = "Write correct Email id")
    @NotEmpty(message = "Enter Email_Id")
    private String emailId;
}
