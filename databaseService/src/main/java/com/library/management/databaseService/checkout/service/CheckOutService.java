package com.library.management.databaseService.checkout.service;

import com.library.management.databaseService.checkout.entity.Checkout;
import com.library.management.databaseService.checkout.model.CheckOutList;
import com.library.management.databaseService.checkout.model.CheckOutRequest;
import com.library.management.databaseService.checkout.repository.CheckOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CheckOutService {

    @Autowired
    CheckOutRepository checkOutRepository;

    public String doCheckOut(CheckOutList listOfCheckout) {
        List<Checkout> checkoutList=new ArrayList<>();
       listOfCheckout.getCheckOutList().stream().forEach(s->
       { Checkout checkout=new Checkout();
           checkout.setId(s.getId());
           checkout.setBookId(s.getBookId());
           checkout.setUserId(s.getUserId());
           checkout.setCheckOutDate(s.getCheckOutDate());
           checkout.setExpectedReturnDate(s.getExpectedReturnDate());
           checkout.setRemainingRenewals(s.getRemainingRenewals());
           checkoutList.add(checkout);

       });

        checkOutRepository.saveAll(checkoutList);
        return "Checked Out";

    }

    public CheckOutList printCheckOutOfUser(Integer userid) {
        if (checkOutRepository.findByUserId(userid)!=null) {
            List<Checkout> checkouts = checkOutRepository.findByUserId(userid);
            List<CheckOutRequest> checkOutLists = new ArrayList<>();
            checkouts.stream().forEach(s -> {
                CheckOutRequest request = new CheckOutRequest();
                request.setId(s.getId());
                request.setBookId(s.getBookId());
                request.setUserId(s.getUserId());
                request.setCheckOutDate(s.getCheckOutDate());
                request.setExpectedReturnDate(s.getExpectedReturnDate());
                request.setRemainingRenewals(s.getRemainingRenewals());
                checkOutLists.add(request);

            });

            CheckOutList response = new CheckOutList();
            response.setCheckOutList(checkOutLists);

            return response;

        }
        else
        {
            return null;
        }
    }

    public String updateCheckOut(CheckOutRequest request, Integer userid,String bookid) {

        Checkout requested = checkOutRepository.findByUserIdAndBookId(userid, bookid);

        if(requested!=null) {

            requested.setId(request.getId());
            requested.setUserId(request.getUserId());
            requested.setBookId(request.getBookId());
            requested.setCheckOutDate(request.getCheckOutDate());
            requested.setExpectedReturnDate(request.getExpectedReturnDate());
            requested.setRemainingRenewals(request.getRemainingRenewals());

            checkOutRepository.save(requested);

        }
        return "Updated";

        }

        public String deleteCheckOut(Integer userid,String bookid){

            Checkout requested=checkOutRepository.findByUserIdAndBookId(userid,bookid);
        if(requested!=null){
            checkOutRepository.delete(requested);
            return "Deleted";
        }
        return null;
        }

}
