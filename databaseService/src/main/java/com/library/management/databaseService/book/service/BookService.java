package com.library.management.databaseService.book.service;

import com.library.management.databaseService.book.model.BookDetailsRequest;
import com.library.management.databaseService.book.entity.Book_details;
import com.library.management.databaseService.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<BookDetailsRequest> displayBooks() {

        List<Book_details> listOfBooks=bookRepository.findAll();
        List<BookDetailsRequest> listOfBooksRequest=new ArrayList<>();
        listOfBooks.stream().forEach(s->{
            BookDetailsRequest bookDetailsRequest=new BookDetailsRequest();
            bookDetailsRequest.setIsbn_id(s.getIsbn_id());
            bookDetailsRequest.setBook_title(s.getBookTitle());
            bookDetailsRequest.setBook_author(s.getBookAuthor());
            bookDetailsRequest.setBook_language(s.getBook_language());
            bookDetailsRequest.setCategory(s.getCategory());
            bookDetailsRequest.setTotal_no_of_copies(s.getTotal_no_of_copies());
            bookDetailsRequest.setPresent_no_of_copies(s.getPresent_no_of_copies());
            bookDetailsRequest.setPublication_year(s.getPublication_year());
            listOfBooksRequest.add(bookDetailsRequest);
        });
        return listOfBooksRequest;
    }

    public String addBooks(BookDetailsRequest bookDetailsRequest) {
        Book_details detailsOfBook=new Book_details();
        detailsOfBook.setIsbn_id(bookDetailsRequest.getIsbn_id());
        detailsOfBook.setBookAuthor(bookDetailsRequest.getBook_author());
        detailsOfBook.setBookTitle(bookDetailsRequest.getBook_title());
        detailsOfBook.setBook_language(bookDetailsRequest.getBook_language());
        detailsOfBook.setCategory(bookDetailsRequest.getCategory());
        detailsOfBook.setPublication_year(bookDetailsRequest.getPublication_year());
        detailsOfBook.setTotal_no_of_copies(bookDetailsRequest.getTotal_no_of_copies());
        detailsOfBook.setPresent_no_of_copies(bookDetailsRequest.getPresent_no_of_copies());
        bookRepository.save(detailsOfBook);
        return detailsOfBook.getIsbn_id();
    }

    public String updateBooks(BookDetailsRequest bookUpdate, String id) {

        if(bookRepository.existsById(id)){

            Book_details updateDetails=bookRepository.findById(id).get();
            updateDetails.setIsbn_id(bookUpdate.getIsbn_id());
            updateDetails.setBookTitle(bookUpdate.getBook_title());
            updateDetails.setBookAuthor(bookUpdate.getBook_author());
            updateDetails.setCategory(bookUpdate.getCategory());
            updateDetails.setBook_language(bookUpdate.getBook_language());
            updateDetails.setTotal_no_of_copies(bookUpdate.getTotal_no_of_copies());
            updateDetails.setPresent_no_of_copies(bookUpdate.getPresent_no_of_copies());
            updateDetails.setPublication_year(bookUpdate.getPublication_year());
            bookRepository.save(updateDetails);
            return updateDetails.getIsbn_id();
        }
        return null;
    }

    public List<BookDetailsRequest> searchBooks(String bookTitle,String bookAuthor) {

     List<Book_details> displayBooks=bookRepository.findByBookTitleOrBookAuthor(bookTitle,bookAuthor);
     List<BookDetailsRequest> displayBooksList=new ArrayList<>();
     displayBooks.stream().forEach(s->{
         BookDetailsRequest displayBooksRequest=new BookDetailsRequest();
         displayBooksRequest.setIsbn_id(s.getIsbn_id());
         displayBooksRequest.setBook_title(s.getBookTitle());
         displayBooksRequest.setBook_author(s.getBookAuthor());
         displayBooksRequest.setBook_language(s.getBook_language());
         displayBooksRequest.setCategory(s.getCategory());
         displayBooksRequest.setPublication_year(s.getPublication_year());
         displayBooksRequest.setPresent_no_of_copies(s.getPresent_no_of_copies());
         displayBooksRequest.setTotal_no_of_copies(s.getTotal_no_of_copies());
         displayBooksList.add(displayBooksRequest);
     });
     return displayBooksList;



    }

    public String deleteBooks(String id) {
        if(bookRepository.existsById(id)){
            Book_details book=bookRepository.getById(id);
            bookRepository.deleteById(id);
            return book.getIsbn_id();
        }
        return "Id doesn't exists";
    }


    public void incrementOfBooksAvailability(String bookId) {
         Optional<Book_details> book_details=bookRepository.findById(bookId);
         book_details.get().setPresent_no_of_copies(book_details.get().getPresent_no_of_copies()+1);
         bookRepository.save(book_details.get());


    }

    public void decrementOfBooksAvailability(String bookId) {
        Optional<Book_details> book_details=bookRepository.findById(bookId);
        book_details.get().setPresent_no_of_copies((book_details.get().getPresent_no_of_copies())-1);
        bookRepository.save(book_details.get());
    }
}
