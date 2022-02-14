package com.library.management.bookManagementService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
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


}

