package com.library.management.databaseService.book.repository;

import com.library.management.databaseService.book.entity.Book_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book_details,String> {

    List<Book_details> findByBookTitle(String bookTitle);
    List<Book_details> findByBookTitleOrBookAuthor(String bookTitle,String bookAuthor);

}
