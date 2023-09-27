package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.DTO.BookDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.exception.CustomException;
import com.bridgelabz.bookstoreapp.model.BookModel;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BookServiceInterface {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    JWTToken jwtToken;
//========================================================================

    @Override // Update book quantity by authorization user(token).
    public BookModel changeBookQty(String token, int bookId, Long quantity) {
        long user_id = jwtToken.decodeToken(token);
        BookModel bookModel = getById(bookId);
        if (bookModel != null)
            bookModel.setQuantity(quantity);
        bookRepository.save(bookModel);
        return bookModel;
    }
    //===================================================================
    @Override  // Update book prince by authorization user(token).
    public BookModel changeBookPrice(String token, int bookId, double price) {
        long user_id = jwtToken.decodeToken(token);
        BookModel bookModel = getById(bookId);
        if (user_id != 0)
            bookModel.setBookPrice(price);
        bookRepository.save(bookModel);
        return bookModel;
    }
    //===================================================================
    @Override  // Checking Book Availability on database. If book available, then increase by 1 OR
               // if not then adding on database.
    public boolean checkingBookAvailability(String bookName) {
        BookModel book = bookRepository.findByBookName(bookName);
        if (book != null){
            Long currentQty = book.getQuantity();
            book.setQuantity(currentQty+1);
            bookRepository.save(book);
            return false;
        }
        return true;
    }

    //===================================================================
    //  CURD OPERATION
    @Override
    public BookModel add(BookDTO bookDTO) {
        BookModel bookModel = new BookModel(bookDTO);
        bookRepository.save(bookModel);
        return bookModel;
    }

    //------------------------------------------------------------------------
    @Override
    public BookModel getById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new CustomException("Book id " + id + " is Not Present"));
    }

    //------------------------------------------------------------------------
    @Override
    public List<BookModel> getAll() {
        return bookRepository.findAll();
    }

    //------------------------------------------------------------------------
    @Override
    public BookModel update(Integer id, BookDTO bookDTO) {
        BookModel getId = getById(id);
        if (getId != null)
            getId.updateBook(bookDTO);
        return bookRepository.save(getId);
    }


    //------------------------------------------------------------------------
    @Override
    public void delete(long id) {
        getById(id);
        bookRepository.deleteById(id);
    }
}
