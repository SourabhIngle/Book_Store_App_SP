package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.DTO.OrderDTO;
import com.bridgelabz.bookstoreapp.exception.CustomException;
import com.bridgelabz.bookstoreapp.model.BookModel;
import com.bridgelabz.bookstoreapp.model.CartModel;
import com.bridgelabz.bookstoreapp.model.OrderModel;
import com.bridgelabz.bookstoreapp.model.UserModel;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.repository.OrderRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.util.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService implements OrderServiceInterface {
    @Autowired
    JWTToken jwtToken;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;

    @Override
    public OrderModel placeOrder(OrderDTO orderDTO) {
        UserModel userModel = userRepository.findById(orderDTO.getUserId()).orElseThrow(() -> new CustomException("User Not Pressent"));
        BookModel bookModel = bookRepository.findById(orderDTO.getBookId()).orElseThrow(() -> new CustomException("Book Not Present"));
        CartModel cartModel = cartRepository.findByUserAndBook(userModel.getUser_id(), bookModel.getBook_id());

        long orderQuantity = orderDTO.getQty();
        long availableQuantity = bookModel.getQuantity();

        if (orderQuantity <= availableQuantity) {

            double bookTotalPrice = orderQuantity * bookModel.getBookPrice();

            OrderModel orderPlace = new OrderModel();
            orderPlace.setUser(userModel);
            orderPlace.setBook(bookModel);
            orderPlace.setPrice(bookTotalPrice);
            orderPlace.setQuantity(orderQuantity);
            orderPlace.setAddress(orderDTO.getAddress());

            long bookQuantity = availableQuantity - orderQuantity;
            bookModel.setQuantity(bookQuantity);
            bookRepository.save(bookModel);
            orderRepository.save(orderPlace);
            if (cartModel != null) {
                cartRepository.delete(cartModel);
            } else {
                return orderPlace;
            }
            return orderPlace;
        } else {
            throw new CustomException("Quantity more then available stock item");
        }
    }

    @Override
    public List<OrderModel> getAllOrderByUserToken(String token) {
        long id = jwtToken.decodeToken(token);

        List<OrderModel> orderModels = orderRepository.findOrderIdByUserId(id);
        if (!orderModels.isEmpty()) {
            return orderModels;
        } else {
            throw new CustomException("No orders found for this user.");
        }
    }

    @Override
    public OrderModel cancelOrder(String token, Long orderId) {
        long userId = jwtToken.decodeToken(token);
        // Retrieve the order by ID and user ID
        OrderModel order = orderRepository.findByIdByUserId(orderId, userId);
        BookModel bookQuantity = bookRepository.findBookIdByorderId(order.getBook().getBook_id());
        long restoreBookQuantity = order.getQuantity() + bookQuantity.getQuantity();
        if (order != null) {
            order.setCancel(true);
            orderRepository.save(order);
            bookQuantity.setQuantity(restoreBookQuantity);
            bookRepository.save(bookQuantity);
            return order; // Cancellation successful
        } else {
            throw new CustomException("Order id not present"); // Order not found or doesn't belong to the user
        }
    }

    @Override
    public List<OrderModel> getAllOrder() {
        List<OrderModel> orderList = orderRepository.findAllIdByOrderItem();
        if (orderList != null)
            return orderList;
        else
            throw new CustomException("There is no pending order");

    }


}
