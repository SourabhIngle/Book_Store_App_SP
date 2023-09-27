package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.DTO.OrderDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.OrderModel;
import com.bridgelabz.bookstoreapp.service.OrderServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceInterface orderServiceInterface;
    @PostMapping("/placeorder")
    public ResponseEntity<ResponseDTO> placeOrder(@Valid @RequestBody OrderDTO orderDTO){
       ResponseDTO responseDTO= new ResponseDTO("Order Placed Successfully", orderServiceInterface.placeOrder(orderDTO));
       return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }


    @GetMapping("/getallordersforuser")
    public ResponseEntity<ResponseDTO> getAllOrdersForUser(@RequestHeader String token){
        ResponseDTO responseDTO = new ResponseDTO("This is your order list",orderServiceInterface.getAllOrderByUserToken(token));
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }

    @PutMapping("/cancelorder/{orderId}")
    public ResponseEntity<ResponseDTO> cancelOrderByOrderAndOrderId(@RequestHeader String token,@PathVariable Long orderId){
        ResponseDTO responseDTO = new ResponseDTO("This is your order list",orderServiceInterface.cancelOrder(token,orderId));
        if (responseDTO != null) {

            return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
        }else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

//Only completed ordered not cancelled order.
    @GetMapping("/getallorderdetails")
    public ResponseEntity<ResponseDTO> getAllOrderDetails(){
        ResponseDTO responseDTO= new ResponseDTO("This is user order list",orderServiceInterface.getAllOrder());
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }

}
