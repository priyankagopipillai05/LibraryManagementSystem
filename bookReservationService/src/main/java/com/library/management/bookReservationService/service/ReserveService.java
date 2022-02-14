package com.library.management.bookReservationService.service;

import com.library.management.bookReservationService.model.ReserveRequest;
import com.library.management.bookReservationService.model.ReserveRequestList;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReserveService {

    @Autowired
    RestTemplate restTemplate;


    public String bookReserve(ReserveRequest reserveRequest) {

        Date currentDate=new Date();
        reserveRequest.setBookId(reserveRequest.getBookId());
        reserveRequest.setUserId(reserveRequest.getUserId());
        reserveRequest.setReservationDate(currentDate);
        bookReserveCall(reserveRequest);

        return "Reserved";
    }

    private String bookReserveCall(ReserveRequest reserveRequest) {
        String url="http://databaseservice/dbservice/reservebook";
       // String url="http://localhost:8100/dbservice/reservebook";
        HttpEntity<ReserveRequest> request=new HttpEntity<>(reserveRequest);
        return restTemplate.postForObject(url,request,String.class);

      // return restTemplate.exchange(url, HttpMethod.POST,request,String.class).getBody();

    }

    public ReserveRequestList getReserveBookListByBookId(String bookId) {
        String url="http://databaseservice/dbservice/reservebook/bookid/{bookId}";
        Map<String,String> uriVariables=new HashMap<>();
        uriVariables.put("bookId",bookId);
        return restTemplate.getForEntity(url,ReserveRequestList.class,uriVariables).getBody();
    }
}
