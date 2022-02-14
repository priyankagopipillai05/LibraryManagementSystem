package com.library.management.databaseService.reserve.service;

import com.library.management.databaseService.reserve.entity.Reserve;
import com.library.management.databaseService.reserve.model.ReserveRequest;
import com.library.management.databaseService.reserve.model.ReserveRequestList;
import com.library.management.databaseService.reserve.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReserveService {

    @Autowired
    ReserveRepository reserveRepository;

    public String postReserve(ReserveRequest reserveRequest) {

        Reserve reserveDetails=new Reserve();
        reserveDetails.setBookId(reserveRequest.getBookId());
        reserveDetails.setUserId(reserveRequest.getUserId());
        reserveDetails.setReservationDate(reserveRequest.getReservationDate());
        reserveRepository.save(reserveDetails);
        return "Reserved";
    }

    public List<ReserveRequest> getReservedBooksWithBookId(String bookId) {

    List<Reserve> reserveList  =reserveRepository.findByBookId(bookId);

    List<ReserveRequest> listOfReserveRequest=new ArrayList<>();


    reserveList.stream().forEach(s->{
        ReserveRequest reserveRequest=new ReserveRequest();
        reserveRequest.setId(s.getId());
        reserveRequest.setBookId(s.getBookId());
        reserveRequest.setUserId(s.getUserId());
        reserveRequest.setReservationDate(s.getReservationDate());
        listOfReserveRequest.add(reserveRequest);

    });
    return listOfReserveRequest;
    }
}
