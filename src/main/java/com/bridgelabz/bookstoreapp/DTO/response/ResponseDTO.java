package com.bridgelabz.bookstoreapp.DTO.response;

import com.bridgelabz.bookstoreapp.model.BookModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    public String message;
    public Object data;

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(String message) {
        this.message = message;
    }

    public ResponseDTO(Object bookModel) {
        this.data=bookModel;
    }

}

