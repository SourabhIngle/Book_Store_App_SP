package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.DTO.CartDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.CartModel;
import com.bridgelabz.bookstoreapp.model.UserModel;

import java.util.List;

public interface CartServiceInterface {

//    CartModel addToCart(String token,long bookId,long quality);

    CartModel addToCart(CartDTO cartDTO);

    CartModel getById(long cartId);

    void removeById(long id);
//
List<CartModel> removeByToken(String token);

    ResponseDTO getAllCartItems();

    List<CartModel> getAllItemsForUser(String token);

    CartModel updateQty(String token, long cartId, int quantity);
}
