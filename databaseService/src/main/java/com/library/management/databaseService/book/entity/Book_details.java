package com.library.management.databaseService.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="book_details")
public class Book_details {

    @Id
    private String isbn_id;
    @Column(name="book_title")
    private String bookTitle;
    @Column(name="book_author")
    private String bookAuthor;
    private String book_language;
    private String category;
    private Long publication_year;
    private Long total_no_of_copies;
    private Long present_no_of_copies;

    public String getIsbn_id() {
        return isbn_id;
    }

    public void setIsbn_id(String isbn_id) {
        this.isbn_id = isbn_id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBook_language() {
        return book_language;
    }

    public void setBook_language(String book_language) {
        this.book_language = book_language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(Long publication_year) {
        this.publication_year = publication_year;
    }

    public Long getTotal_no_of_copies() {
        return total_no_of_copies;
    }

    public void setTotal_no_of_copies(Long total_no_of_copies) {
        this.total_no_of_copies = total_no_of_copies;
    }

    public Long getPresent_no_of_copies() {
        return present_no_of_copies;
    }

    public void setPresent_no_of_copies(Long present_no_of_copies) {
        this.present_no_of_copies = present_no_of_copies;
    }
}
