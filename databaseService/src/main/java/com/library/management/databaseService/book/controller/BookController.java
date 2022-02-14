package com.library.management.databaseService.book.controller;

import com.library.management.databaseService.book.model.BookDetailsRequest;
import com.library.management.databaseService.book.model.BookList;
import com.library.management.databaseService.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/dbservice/books")
    public BookList displayBooks(){
        List<BookDetailsRequest> displayBookList= bookService.displayBooks();
        BookList bookList=new BookList();
        bookList.setBookDetailsList(displayBookList);
        return bookList;
    }

    @PostMapping("/dbservice/books")
    public String addBooks(@RequestBody BookDetailsRequest bookDetails) {
        return bookService.addBooks(bookDetails);

    }

    @PostMapping("/dbservice/books/{bookId}/increment")
    public void incrementBooksAvailability(@PathVariable String bookId){
        bookService.incrementOfBooksAvailability(bookId);

    }

    @PostMapping("/dbservice/books/{bookId}/decrement")
    public void decrementBooksAvailability(@PathVariable String bookId){
        bookService.decrementOfBooksAvailability(bookId);

    }

    @PutMapping("/dbservice/books/{id}")
    public String updateBooks(@RequestBody BookDetailsRequest bookUpdate,@PathVariable String id){
        return bookService.updateBooks(bookUpdate,id);
    }

    @GetMapping("/dbservice/search/books")
    public BookList searchBooks(@RequestParam(required = false) String bookTitle,@RequestParam(required = false) String bookAuthor){
        List<BookDetailsRequest> displayBookList= bookService.searchBooks(bookTitle,bookAuthor);
        BookList bookList=new BookList();
        bookList.setBookDetailsList(displayBookList);
        return bookList;
    }

    @DeleteMapping("/dbservice/books/{id}")
    public String deleteBooks(@PathVariable String id){
        return bookService.deleteBooks(id);
    }


}
