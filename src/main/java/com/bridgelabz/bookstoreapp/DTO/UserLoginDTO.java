package com.bridgelabz.bookstoreapp.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDTO {
    @NotNull
   public String emailId;
    @NotNull
    public String password;
}
