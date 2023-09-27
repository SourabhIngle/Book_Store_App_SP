package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.DTO.CartDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.BookModel;
import com.bridgelabz.bookstoreapp.model.CartModel;
import com.bridgelabz.bookstoreapp.service.BookService;
import com.bridgelabz.bookstoreapp.service.CartServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServiceInterface cartServiceInterface;
    @Autowired
    BookService bookService;


    @PostMapping("/addtocart")
    public ResponseEntity<ResponseDTO> addToCart(@Valid @RequestBody CartDTO cartDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Book added on your cart successfully", cartServiceInterface.addToCart(cartDTO));

        if (responseDTO != null)
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        else
            responseDTO = new ResponseDTO("Book out of stock",null);
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getbyid/{cartId}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable long cartId) {
        ResponseDTO responseDTO = new ResponseDTO("This is your cart detailed.", cartServiceInterface.getById(cartId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/removebyid/{id}")
    public ResponseEntity<String> removeById(@PathVariable long id) {
        cartServiceInterface.removeById(id);
        return ResponseEntity.ok("Book remove from your cart");
    }

    @DeleteMapping("/removebytoken")
    public ResponseEntity<String> removeById(@RequestHeader String token) {
        if (cartServiceInterface.removeByToken(token) != null) {
            return ResponseEntity.ok("Book remove from your cart using user Id");
        }
        return ResponseEntity.ok("Cart is empty");
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllCartDetails() {
        ResponseDTO responseDTO = cartServiceInterface.getAllCartItems();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @GetMapping("/getallcartitembyusertoken")
    public ResponseEntity<ResponseDTO> getAllCartItemsByUserToken(@RequestHeader String token) {
         ResponseDTO responseDTO = new ResponseDTO("Get all cart item by user .", cartServiceInterface.getAllItemsForUser(token));
            return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    @PatchMapping("/updatequantity") //  RequestParam => changebookqty?cartId=1&quantity=12
    public ResponseEntity<ResponseDTO> changeBookQuantity(@Valid @RequestHeader String token,
                                                          @RequestParam long cartId,
                                                          @RequestParam int quantity) {
        ResponseDTO responseDTO =new ResponseDTO("Update quantity in your cart", cartServiceInterface.updateQty(token, cartId, quantity));
//        ResponseDTO responseDTO = new ResponseDTO("Book quantity has been updated", cartModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);}
//    }

}

