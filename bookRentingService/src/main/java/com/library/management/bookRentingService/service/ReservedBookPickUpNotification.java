package com.library.management.bookRentingService.service;

import com.library.management.bookRentingService.model.MailContents;
import com.library.management.bookRentingService.model.ReserveRequest;
import com.library.management.bookRentingService.model.ReserveRequestList;
import com.library.management.bookRentingService.model.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReservedBookPickUpNotification {

    Logger logger = LoggerFactory.getLogger(ReservedBookPickUpNotification.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MailContents mailContents;


    //@Async
    public void getReserveBookListByBookId(String bookId) {
        String url="http://databaseservice/dbservice/reservebook/bookid/{bookId}";
        Map<String,String> uriVariables=new HashMap<>();
        uriVariables.put("bookId",bookId);

        logger.info("Inside getReserveBookList {} ",bookId);

        ReserveRequestList reserveRequestList =restTemplate.getForEntity(url,ReserveRequestList.class,uriVariables).getBody();

        if(reserveRequestList!=null && reserveRequestList.getReserveRequestList()!=null
                && !reserveRequestList.getReserveRequestList().isEmpty()) {
            List<ReserveRequest> sortedListByBookId = reserveRequestList.getReserveRequestList()
                    .stream().sorted((x, y) -> x.getId().compareTo(y.getId())).collect(Collectors.toList());
            ReserveRequest reserveRequest=sortedListByBookId.get(0);

            logger.info("User ID is {} ",reserveRequest.getUserId());

           String userEmailAddress= getUserEmailAddress(reserveRequest.getUserId());
            logger.info("EmaiAddress is {} ",userEmailAddress);
           mailContents.setTo(userEmailAddress);
           mailContents.setBookId(reserveRequest.getBookId());
           mailContents.setMessageType("ReservationPickUp");
           mailContents.setSubject("Book Reservation Status");
           mailContents.setText("Your requested book with bookid"+reserveRequest.getBookId()+"is ready for pickup");
           sendReservedBookPickUpNotificationMail(mailContents);

        }
    }

    private String sendReservedBookPickUpNotificationMail(MailContents mailContents) {
        String url="http://notificationservice/notificationservice/reservedbook/pickup/mail";
        HttpEntity<MailContents> request=new HttpEntity<>(mailContents);
        return restTemplate.postForObject(url,request,String.class);
    }

    private String getUserEmailAddress(Integer userId) {
        logger.info("User ID is {}",userId);
        String url="http://databaseservice/dbservice/user/id/{userId}";
        Map<String,String> uriVariable=new HashMap<>();
        uriVariable.put("userId",String.valueOf(userId));
        logger.info("Map is "+uriVariable);
        UserRequest userDetails=restTemplate.getForEntity(url, UserRequest.class,uriVariable).getBody();
        return userDetails.getEmail_id();
    }

}
