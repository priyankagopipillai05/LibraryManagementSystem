package com.library.management.bookRentingService.controller;

import com.library.management.bookRentingService.model.RenewResponseList;
import com.library.management.bookRentingService.model.RentBookList;
import com.library.management.bookRentingService.service.BookRentingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRentingController {

    @Autowired
    BookRentingService bookRentingService;

    @PostMapping("/bookrenting/checkout")
    public String doCheckOut(@RequestBody RentBookList rentBookList){
        return bookRentingService.doCheckOut(rentBookList);
    }


    @PostMapping("/bookrenting/returnbook")
    public String doReturnBook(@RequestBody RentBookList rentBookList){
        return bookRentingService.doReturnBook(rentBookList);
    }

    @PostMapping("/bookrenting/renewbook")
    public RenewResponseList doRenewBook(@RequestBody RentBookList rentBookList){
        return bookRentingService.doRenewBook(rentBookList);
    }



}
