package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.DTO.BookDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_data")
public class BookModel {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private Long book_id;
    private String bookName;
    private String authorName;
    private String description;
    private String logo;
    private Double bookPrice;
    private Long quantity;

    public BookModel(BookDTO bookDTO) {
        this.updateBook(bookDTO);
    }

    public void updateBook(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.authorName = bookDTO.getAuthorName();
        this.description = bookDTO.getDescription();
        this.logo = bookDTO.getLogo();
        this.bookPrice = bookDTO.getBookPrice();
        this.quantity = bookDTO.getQuantity();
    }
}
