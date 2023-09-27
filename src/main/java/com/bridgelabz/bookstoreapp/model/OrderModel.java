package com.bridgelabz.bookstoreapp.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "order_data")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate date = LocalDate.now();
    private Double price;
    private Long quantity;
    private String address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book;
    private boolean cancel;

    public OrderModel() {
    }

    public OrderModel(LocalDate date, double price, long quantity, String address, UserModel user, BookModel book, boolean cancel) {
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.user = user;
        this.book = book;
        this.cancel = cancel;
    }
}
