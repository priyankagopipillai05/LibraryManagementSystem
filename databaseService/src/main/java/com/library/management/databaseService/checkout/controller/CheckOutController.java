package com.library.management.databaseService.checkout.controller;

import com.library.management.databaseService.checkout.entity.Checkout;
import com.library.management.databaseService.checkout.model.CheckOutList;
import com.library.management.databaseService.checkout.model.CheckOutRequest;
import com.library.management.databaseService.checkout.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckOutController {

    @Autowired
    CheckOutService checkOutService;

    @GetMapping("/dbservice/checkout/{userid}")
    public CheckOutList printCheckOutOfUser(@PathVariable Integer userid){
        return checkOutService.printCheckOutOfUser(userid);
    }

    @PostMapping("/dbservice/checkout")
        public String doCheckOut(@RequestBody CheckOutList listOfCheckOut){
            return checkOutService.doCheckOut(listOfCheckOut);

    }

    @PutMapping("/dbservice/checkout/user/{userid}/book/{bookid}")
    public String updateCheckOut(@RequestBody CheckOutRequest request,@PathVariable Integer userid,
                                 @PathVariable String bookid){
        return checkOutService.updateCheckOut(request,userid,bookid);
    }

    @DeleteMapping("/dbservice/checkout/user/{userid}/book/{bookid}")
    public String deleteCheckOut(@PathVariable Integer userid,@PathVariable String bookid){

        return checkOutService.deleteCheckOut(userid,bookid);
    }

    }

