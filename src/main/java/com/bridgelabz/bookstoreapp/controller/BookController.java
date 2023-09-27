package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.DTO.BookDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.BookModel;
import com.bridgelabz.bookstoreapp.service.BookServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins ="http://localhost:4200/")
public class BookController {

    @Autowired
    private BookServiceInterface bookServiceInterface;

//==========================================================
//Add book details on database API

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> add(@Valid @RequestBody BookDTO bookDTO) {
        boolean bookName = bookServiceInterface.checkingBookAvailability(bookDTO.getBookName());
        ResponseDTO responseDTO;
        if (bookName) {
            responseDTO = new ResponseDTO("Book details Added Successfully", bookServiceInterface.add(bookDTO));
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        responseDTO = new ResponseDTO("This Book increasing by 1 quantity");
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable Integer id) {
        ResponseDTO responseDTO = new ResponseDTO("Get book details from database Successfully", bookServiceInterface.getById(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public List<BookModel> getAll() {
        return bookServiceInterface.getAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateById(@PathVariable Integer id, @Valid @RequestBody BookDTO bookDTO) {
        BookModel bookModel = bookServiceInterface.update(id, bookDTO);
        if (bookModel != null) {
            ResponseDTO responseDTO = new ResponseDTO("Update book details Successfully", bookModel);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        bookServiceInterface.delete(id);
        return ResponseEntity.ok("Book Id " + id + " has been Successfully deleted");
    }

    @PatchMapping("/changebookqty") //  RequestParam => chengebookqty?bookId=1&quantity=12
    public ResponseEntity<ResponseDTO> changeBookQuantity(@RequestHeader String token,
                                                          @RequestParam int bookId,
                                                          @RequestParam long quantity) {
        BookModel bookModel = bookServiceInterface.changeBookQty(token, bookId, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Book quantity has been updated", bookModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/changebookprice") //  RequestParam => chengebookprice?bookId=1&quantity=12
    public ResponseEntity<ResponseDTO> changeBookPrice(@RequestHeader String token,
                                                          @PathVariable int bookId,
                                                          @PathVariable double price) {
        BookModel bookModel = bookServiceInterface.changeBookPrice(token, bookId, price);
        ResponseDTO responseDTO = new ResponseDTO("Book price has been updated", bookModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }
}
