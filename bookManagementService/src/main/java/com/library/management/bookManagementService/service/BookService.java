package com.library.management.bookManagementService.service;

import com.library.management.bookManagementService.model.BookDetailsRequest;
import com.library.management.bookManagementService.model.BookList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    RestTemplate restTemplate;

    public BookList displayBooks() {
        String url="http://databaseservice/dbservice/books";

        ResponseEntity<BookList> responseEntity=restTemplate.getForEntity(url, BookList.class);
        return responseEntity.getBody();


    }

    public String addBooks(BookDetailsRequest bookDetails) {
        String url="http://databaseservice/dbservice/books";
        HttpEntity<BookDetailsRequest> httpEntity=new HttpEntity<>(bookDetails);
       return restTemplate.postForObject(url,httpEntity,String.class);

    }

    public String updateBooks(BookDetailsRequest bookUpdate, String id) {
        String url = "http://databaseservice/dbservice/books/{id}";
        HttpEntity<BookDetailsRequest> httpEntity = new HttpEntity<>(bookUpdate);
        HashMap<String, String> obj = new HashMap<>();
        obj.put("id", id);
         ResponseEntity<String> responseEntity=restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class, obj);
         return responseEntity.getBody();
    }


    public BookList searchBooks(String bookTitle, String bookAuthor) {
        String url="http://databaseservice/dbservice/search/books?bookAuthor={bookAuthor}&bookTitle={bookTitle}";

        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("bookAuthor",bookAuthor);
        uriVariables.put("bookTitle",bookTitle);

        ResponseEntity<BookList> responseEntity =
                restTemplate.exchange(url,HttpMethod.GET,null, BookList.class,uriVariables);

        return responseEntity.getBody();


    }

    public String deleteBooks(String id) {
        String url="http://databaseservice/dbservice/books/{id}";

        Map<String,String> obj=new HashMap<>();
        obj.put("id",id);
       ResponseEntity<String> responseEntity=restTemplate.exchange(url,HttpMethod.DELETE,null,String.class,obj);
        return responseEntity.getBody();
    }
}
