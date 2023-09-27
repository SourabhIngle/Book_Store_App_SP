package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_data")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate registeredDate;
    private LocalDate updatedDate;
    private String password;
    private String emailId;
    private Boolean verify;
    private Integer otp;

    public UserModel(UserDTO userDTO) {
        this.updateUserData(userDTO);
    }

   public void updateUserData(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.dateOfBirth = userDTO.getDateOfBirth();
        this.registeredDate = getRegisteredDate();
        this.updatedDate = getUpdatedDate();
        this.password = userDTO.getPassword();
        this.emailId = userDTO.getEmailId();
        this.verify = getVerify();
        this.otp = getOtp();
    }
}
