package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.BookModel;
import com.bridgelabz.bookstoreapp.model.CartModel;
import com.bridgelabz.bookstoreapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Long> {

    @Query(value = "SELECT * FROM cart_data WHERE user_id =:userId", nativeQuery = true)
    List<CartModel> findCartIdByUserid(Long userId);

    @Query(value = "SELECT * FROM cart_data WHERE user_id =:userModel AND book_id =:bookModel", nativeQuery = true)
    CartModel findByUserAndBook(long userModel, long bookModel);

    @Query(value = "SELECT * FROM cart_data WHERE cart_id  =:cartId AND user_id =:userId", nativeQuery = true)
    CartModel findByIdAndUserId(long cartId, long userId);

}
