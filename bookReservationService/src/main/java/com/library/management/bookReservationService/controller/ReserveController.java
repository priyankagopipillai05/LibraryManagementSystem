package com.library.management.bookReservationService.controller;

import com.library.management.bookReservationService.model.ReserveRequest;
import com.library.management.bookReservationService.model.ReserveRequestList;
import com.library.management.bookReservationService.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReserveController {

    @Autowired
    ReserveService reserveService;

    @PostMapping("/bookreservationservice/reservebook")
    public String bookReserve(@RequestBody ReserveRequest reserveRequest){
        return reserveService.bookReserve(reserveRequest);
    }

    @GetMapping("/bookreservationservice/reservebook/bookid/{bookId}")
    public ReserveRequestList getReserveBookListByBookId(@PathVariable String bookId){
        return reserveService.getReserveBookListByBookId(bookId);
    }
}
