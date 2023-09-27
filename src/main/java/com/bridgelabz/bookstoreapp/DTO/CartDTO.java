package com.bridgelabz.bookstoreapp.DTO;


import com.bridgelabz.bookstoreapp.model.BookModel;
import com.bridgelabz.bookstoreapp.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO {
   public Long user_id;
   public Long book_id;
   private Integer quantity;
}
