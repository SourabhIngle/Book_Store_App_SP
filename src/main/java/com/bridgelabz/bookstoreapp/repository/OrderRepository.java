package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    @Query(value = "SELECT * FROM order_data WHERE user_id =:userId", nativeQuery = true)
    List<OrderModel> findOrderIdByUserId(long userId);

    @Query(value = "SELECT * FROM order_data WHERE order_id =:orderId AND user_id =:userId", nativeQuery = true)
    OrderModel findByIdByUserId(Long orderId, Long userId);

    @Query(value = "SELECT * FROM order_data WHERE cancel =false", nativeQuery = true)
    List<OrderModel> findAllIdByOrderItem();


}
