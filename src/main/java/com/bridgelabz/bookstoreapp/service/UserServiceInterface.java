package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.DTO.UserLoginDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.DTO.UserDTO;
import com.bridgelabz.bookstoreapp.model.UserModel;

import java.util.List;

public interface UserServiceInterface {

    UserModel register(UserDTO userDTO);

    List<UserModel> getAll();

    boolean uniqueEmailId(String email);

    boolean verification(String email,Integer otp);

    UserModel getById(Long id);

    //------------------------------------------------------------------------
    UserModel getByToken(String token);

    void delete(long id);

    UserModel update(Long id, UserDTO userDTO);

    ResponseDTO login(UserLoginDTO userLoginDTO);

    UserModel loginByToken(String token);
}
