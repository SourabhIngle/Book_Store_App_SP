package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.DTO.CartDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
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
@CrossOrigin(origins = "http://localhost:4200")
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
            responseDTO = new ResponseDTO("Book out of stock", null);
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

    @DeleteMapping("/remove-by-user/{userId}/{bookId}")
    public ResponseEntity<Long> removeById(@PathVariable long userId, @PathVariable long bookId) {
        long cartId = cartServiceInterface.removeUserAndBookId(userId, bookId);
        return ResponseEntity.ok(cartId);
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


    @GetMapping("/getallcartitembyusertoken/{token}")
    public ResponseEntity<ResponseDTO> getAllCartItemsByUserToken(@PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("Get all cart item by user .", cartServiceInterface.getAllItemsForUser(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //  RequestParam => changebookqty?cartId=1&quantity=12
    @PutMapping("/updatequantity/{token}/{cartId}/{quantity}")
    public ResponseEntity<ResponseDTO> changeBookQuantity(@Valid @PathVariable String token,
                                                          @PathVariable long cartId,
                                                          @PathVariable int quantity) {
        ResponseDTO responseDTO = new ResponseDTO("Update quantity in your cart", cartServiceInterface.updateQty(token, cartId, quantity));
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/increase-quantity/{token}/{bookId}")
    public Integer increaseQuantity(@PathVariable String token, @PathVariable long bookId) {

        CartModel updatedCart = cartServiceInterface.increaseQty(token, bookId);
        return updatedCart.getQuantity();

    }

    @PatchMapping("/decrease-quantity/{token}/{cartId}")
    public Integer decreaseQuantity(@PathVariable String token, @PathVariable long bookId) {

        CartModel updatedCart = cartServiceInterface.decreaseQty(token, bookId);
        return updatedCart.getQuantity();

    }


    @GetMapping("/get-cart-id/{userId}/{bookId}")
    public ResponseEntity<ResponseDTO> getCartIdByUserAndBookId(@PathVariable long userId, @PathVariable long bookId) {
        CartModel cartModel = cartServiceInterface.getCartIdByUserAndBookId(userId, bookId);

//       Long cartId = responseDTO.getCart_id();
        if (cartModel != null) {
            ResponseDTO responseDTO = new ResponseDTO("Card id is ", cartModel.getCart_id());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            ResponseDTO responseDTO = new ResponseDTO("Cart id not present", null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }
}

