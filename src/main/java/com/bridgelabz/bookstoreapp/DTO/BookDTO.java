package com.bridgelabz.bookstoreapp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class BookDTO {

  @NotEmpty(message = "Please write the book name")
  private String bookName;
  @NotBlank(message = "Write the Author name.")
  private String authorName;
  @NotEmpty(message = "Enter description here")
  private String description;
  @NotEmpty(message = "This filed can not be empty")
  private String logo;
  @Range(min = 80,message = "Book price should be greater than 80 rupee ")
  private Double bookPrice;
  @Range(min = 1,message = "Quantity should be 1 or more")
  private Long quantity;


}
