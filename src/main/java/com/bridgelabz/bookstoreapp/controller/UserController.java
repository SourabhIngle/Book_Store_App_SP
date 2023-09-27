package com.bridgelabz.bookstoreapp.controller;


import com.bridgelabz.bookstoreapp.DTO.UserLoginDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.DTO.UserDTO;
import com.bridgelabz.bookstoreapp.model.UserModel;
import com.bridgelabz.bookstoreapp.service.UserServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    //========================================================================
//  User registration API
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody UserDTO userDTO) {
        String email = userDTO.getEmailId();
        if (userServiceInterface.uniqueEmailId(email)) {
            ResponseDTO responseDTO = new ResponseDTO("User registered successfully.", userServiceInterface.register(userDTO));
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        ResponseDTO responseDTO = new ResponseDTO("Email Id already exists in Database.Use another Email Id", userDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }


    //========================================================================
//   Login API
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Login is successfully", userServiceInterface.login(userLoginDTO));

        if (responseDTO != null) {
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }else {
            String errorMessage = "Invalid email or password";
            responseDTO = new ResponseDTO(errorMessage, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    //========================================================================
//   User verification API
    @PutMapping("/verificationOTP")//  RequestParam => verificationOTP?email='djkf@gmail.com'&otp='586791'
//    public ResponseEntity<String> verify(@PathVariable String email, @PathVariable Integer otp) {
    public ResponseEntity<String> verify(@RequestParam String email, @RequestParam Integer otp) {
        if (userServiceInterface.verification(email, otp)) {
            return ResponseEntity.ok("Registration successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid email_Id or OTP");
        }
    }

    //========================================================================
//   Login API
    @GetMapping("/login/token")
    public ResponseDTO loginByToken(@RequestHeader String token) {
       UserModel userModel = userServiceInterface.loginByToken(token);
     if (userModel !=null){
         return new ResponseDTO("User data found",userModel);
     }
        return new ResponseDTO("date found", null);
    }

    //=====================CRUD operations=========================================================
//  Get all user data from database------------------------------------------------------------
    @GetMapping("/getall")
    public List<UserModel> getAll() {
        return userServiceInterface.getAll();
    }


    //  Get user data from database using by id------------------------------------------------------------
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable long id) {
        ResponseDTO responseDTO = new ResponseDTO("Data Fetched Successfully", userServiceInterface.getById(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }


    //  Get user data from database using by id------------------------------------------------------------
    @GetMapping("/getbytoken")
    public ResponseEntity<ResponseDTO> getByToken(@RequestHeader String token) {
        ResponseDTO responseDTO = new ResponseDTO("Data Fetched Successfully", userServiceInterface.getByToken(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

//  Delete user data from database using by id------------------------------------------------------------

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        userServiceInterface.delete(id);
        return ResponseEntity.ok("Id " + id + " has been Successfully deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        UserModel userModel = userServiceInterface.update(id, userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Data Update Successfully", userModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}