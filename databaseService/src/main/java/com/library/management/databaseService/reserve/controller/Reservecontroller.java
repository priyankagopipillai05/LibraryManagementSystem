package com.library.management.databaseService.reserve.controller;

import com.library.management.databaseService.reserve.model.ReserveRequest;
import com.library.management.databaseService.reserve.model.ReserveRequestList;
import com.library.management.databaseService.reserve.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Reservecontroller {

    @Autowired
    ReserveService reserveService;

    @PostMapping("/dbservice/reservebook")
    public String postReserve(@RequestBody ReserveRequest reserveRequest){
        return reserveService.postReserve(reserveRequest);
    }

   @GetMapping("/dbservice/reservebook/bookid/{bookId}")
    public ReserveRequestList getReservedBooksWithBookId(@PathVariable String bookId){
        List<ReserveRequest> reserveRequest= reserveService.getReservedBooksWithBookId(bookId);
        ReserveRequestList reserveRequestList=new ReserveRequestList();
        reserveRequestList.setReserveRequestList(reserveRequest);
        return reserveRequestList;

   }

}
