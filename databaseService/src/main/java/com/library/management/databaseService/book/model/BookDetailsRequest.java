package com.library.management.databaseService.book.model;

import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Component
public class BookDetailsRequest {


    private String isbn_id;
    private String book_title;
    private String book_author;
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

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
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
