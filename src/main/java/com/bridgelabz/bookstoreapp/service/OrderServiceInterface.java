package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.DTO.OrderDTO;
import com.bridgelabz.bookstoreapp.model.OrderModel;

import java.util.List;

public interface OrderServiceInterface {
     OrderModel placeOrder(OrderDTO orderDTO) ;

     List<OrderModel> getAllOrderByUserToken(String token);


   OrderModel cancelOrder(String token, Long orderId);

    List <OrderModel> getAllOrder();
}
