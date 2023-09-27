package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.DTO.CartDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.exception.CustomException;
import com.bridgelabz.bookstoreapp.model.BookModel;
import com.bridgelabz.bookstoreapp.model.CartModel;
import com.bridgelabz.bookstoreapp.model.UserModel;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.util.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartService implements CartServiceInterface {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTToken jwtToken;
//
//    @Override
//    public CartModel addToCart(String token, long bookId, long quality) {
//
//    }


    @Override
    public CartModel addToCart(CartDTO cartDTO) {
        UserModel userModel = userRepository.findById(cartDTO.getUser_id())
                .orElseThrow(() -> new CustomException("User id " + cartDTO.getUser_id() + " not found "));
        BookModel bookModel = bookRepository.findById(cartDTO.getBook_id())
                .orElseThrow(() -> new CustomException("Book id " + cartDTO.getBook_id() + " not found "));

        // Check if the item is already in the cart for the user
        CartModel existingCartItem = cartRepository.findByUserAndBook(userModel.getUser_id(), bookModel.getBook_id());

        if (existingCartItem != null) {
            // Update the existing cart item's quantity
            //int newQuantity = existingCartItem.getQuantity() + cartDTO.getQuantity();
            int newQuantity = existingCartItem.getQuantity() + cartDTO.getQuantity();
            existingCartItem.setQuantity(newQuantity);
            double totalPrice = bookModel.getBookPrice() * newQuantity;
            existingCartItem.setTotalPrice(totalPrice);
            cartRepository.save(existingCartItem);
            return existingCartItem;
        } else {
            // Create a new cart item
            CartModel newCartItem = new CartModel(userModel, bookModel, cartDTO.getQuantity());
            double totalPrice = bookModel.getBookPrice() * cartDTO.getQuantity();
            newCartItem.setTotalPrice(totalPrice);
            cartRepository.save(newCartItem);
            return newCartItem;
        }
    }

    @Override
    public CartModel getById(long id) {
        return cartRepository.findById(id).orElseThrow(() -> new CustomException("User id " + id + " not found "));
    }

    @Override
    public void removeById(long id) {
        cartRepository.findById(id).orElseThrow(() -> new CustomException("Id not found"));
        cartRepository.deleteById(id);
    }


    @Override
    public List<CartModel> removeByToken(String token) {
        long id = jwtToken.decodeToken(token);
        List<CartModel> cartModels = cartRepository.findCartIdByUserid(id);
        if (cartModels.isEmpty()) {
            return null;
        } else {
            if (cartModels != null) {
                cartModels.forEach(e -> {
                    if (e != null) {
                        cartRepository.deleteById(e.getCart_id());
                    }
                });
            }
            return cartModels;
        }
    }
//

    @Override
    public ResponseDTO getAllCartItems() {
        List<CartModel> getAllCartItems = cartRepository.findAll();
        if (getAllCartItems.isEmpty())
            throw new CustomException("Cart is empty");
        else
            return new ResponseDTO("This is the list of all items in your cart. ", getAllCartItems);
    }

    @Override
    public List<CartModel> getAllItemsForUser(String token) {
        long id = jwtToken.decodeToken(token);
        List<CartModel> cartItems = cartRepository.findCartIdByUserid(id);
        if (cartItems.isEmpty()) {
            return null;
        } else {
            cartItems.forEach(e -> {
                if (e != null) {
                    cartRepository.findCartIdByUserid(id);
                }
            });
            return cartItems;
        }
    }

    @Override
    public CartModel updateQty(String token, long cartId, int quantity) {
        long userId = jwtToken.decodeToken(token);

        CartModel cartItem = cartRepository.findByIdAndUserId(cartId, userId);

        if (cartItem == null) {
            throw new CustomException("Cart item not found or doesn't belong to the user.");
        }

        cartItem.setQuantity(quantity);
        return cartRepository.save(cartItem);
    }

}
