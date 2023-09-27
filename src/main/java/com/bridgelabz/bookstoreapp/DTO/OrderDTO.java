package com.bridgelabz.bookstoreapp.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @NotNull
    private Long userId;
    @NotNull
    private Long bookId;
    @NotNull
    private Long qty;
    @NotEmpty
    private String address;

}
