package com.library.management.bookManagementService.controller;

import com.library.management.bookManagementService.model.BookDetailsRequest;
import com.library.management.bookManagementService.model.BookList;
import com.library.management.bookManagementService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmanagement")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public BookList displayBooks(){
        return bookService.displayBooks();

    }

    @PostMapping("/books")
    public String addBooks(@RequestBody BookDetailsRequest bookDetails) {
        return bookService.addBooks(bookDetails);

    }

    @PutMapping("/books/{id}")
    public String updateBooks(@RequestBody BookDetailsRequest bookUpdate,@PathVariable String id){
        return bookService.updateBooks(bookUpdate,id);
    }

    @GetMapping("/search/books")
    public BookList searchBooks(@RequestParam(required = false) String bookTitle, @RequestParam(required = false) String bookAuthor){
        return bookService.searchBooks(bookTitle,bookAuthor);
    }

    @DeleteMapping("/books/{id}")
    public String deleteBooks(@PathVariable String id){

        return bookService.deleteBooks(id);
    }


}


