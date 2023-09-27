package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.DTO.CartDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
//OneToMany, ManyToMany, and ElementCollection within the class.
//One to One ,Many to One for diff. classes.
@Entity
@Data
@Table(name = "cart_data")
@NoArgsConstructor
//@AllArgsConstructor
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cart_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user_id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book_id;
    private Integer quantity;
    private Double totalPrice;


    public CartModel(UserModel userModel, BookModel bookModel, Integer quantity) {
        this.user_id=userModel;
        this.book_id=bookModel;
        this.quantity=quantity;
    }

//    }

//    public CartModel(CartModel ) {
//        this.user_Id = user_Id;
//        this.book_Id = book_Id;
//        this.quantity = quantity;
//        this.totalPrice = totalPrice;
//    }
}
