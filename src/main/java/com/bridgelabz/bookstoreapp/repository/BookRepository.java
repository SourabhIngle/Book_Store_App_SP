package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    BookModel findByBookName(String bookName);

    @Query(value = "SELECT * FROM cart_data WHERE book_id =:bookId", nativeQuery = true)
    BookModel findCartIdByBook(long bookId);

    @Query(value = "SELECT * FROM book_data WHERE book_id =:bookId", nativeQuery = true)
    BookModel findBookIdByorderId(long bookId);
}
