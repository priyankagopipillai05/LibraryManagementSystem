package com.library.management.bookRentingService.service;

import com.library.management.bookRentingService.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class BookRentingService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ReservedBookPickUpNotification reservedBookPickUpNotification;

    Logger logger=LoggerFactory.getLogger(BookRentingService.class);

    @Value("${librarymanagement.numberofdaystoreturnbook:7}")
    int numberOfDays;

    @Value("${librarymanagement.numberofbookrenewals:3}")
    int noOfRenewals;

    public String doCheckOut(RentBookList rentBookList) {

        logger.info("Starting  BookRenting Service");



        Date presentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(presentDate);
        cal.add(Calendar.DATE,numberOfDays);
        Date expectedReturnDate = cal.getTime();

        rentBookList.getCheckOutList().stream().forEach(s -> {
            s.setCheckOutDate(presentDate);
            s.setExpectedReturnDate(expectedReturnDate);
            s.setRemainingRenewals(noOfRenewals);
            updateBookAvailabilty(s.getBookId());
        });

        postCheckOutTable(rentBookList);
        return "Checked Out";
    }

    public void updateBookAvailabilty(String bookId) {
        String url = "http://databaseservice/dbservice/books/{bookid}/decrement";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("bookid", bookId);
        restTemplate.postForObject(url, null, Object.class, uriVariables);
    }


    public void postCheckOutTable(RentBookList rentBookList) {
        String url = "http://databaseservice/dbservice/checkout";
        HttpEntity<RentBookList> httpEntity = new HttpEntity<>(rentBookList);
        restTemplate.postForObject(url, httpEntity, String.class);


    }

    public String doReturnBook(RentBookList rentBookList) {

        rentBookList.getCheckOutList().stream().forEach(s -> {
            String bookId = s.getBookId();
            Integer userId = s.getUserId();
            updateBookNumber(bookId);
            deleteFromCheckOutDetails(bookId, userId);
            reservedBookPickUpNotification.getReserveBookListByBookId(bookId);

        });
        return "Returned";
    }

    private void updateBookNumber(String bookId) {
        String url = "http://databaseservice/dbservice/books/{bookId}/increment";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("bookId", bookId);
        restTemplate.postForObject(url, null, Object.class, uriVariables);

    }

    private void deleteFromCheckOutDetails(String bookId, Integer userId) {
        String url = "http://databaseservice/dbservice/checkout/user/{userId}/book/{bookId}";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("bookId", bookId);
        uriVariables.put("userId", String.valueOf(userId));
        restTemplate.delete(url, uriVariables);
    }

    public RenewResponseList doRenewBook(RentBookList rentBookList) {

        RenewResponseList renewResponseList = new RenewResponseList();
        List<RenewBookResponse> renewBookResponseList = new ArrayList<>();

        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE,numberOfDays);
        Date newExpectedReturnDate = cal.getTime();

        Integer userID = rentBookList.getCheckOutList().get(0).getUserId();
        RentBookList checkOutDetails = getCheckOutDetails(userID);

        rentBookList.getCheckOutList().stream().forEach(s -> {
            Optional<RentBook> checkedOutRows = checkOutDetails.getCheckOutList().stream().filter(t -> t.getBookId().equals(s.getBookId())).findAny();
            Long diffInMill = checkedOutRows.get().getExpectedReturnDate().getTime() - currentDate.getTime();
            Long days = TimeUnit.DAYS.convert(diffInMill, TimeUnit.MILLISECONDS);

            RenewBookResponse renewBookResponse= new RenewBookResponse();
            renewBookResponse.setBookID(s.getBookId());
            renewBookResponse.setExpectedReturnDate(newExpectedReturnDate);
            renewBookResponse.setNumberOfRenewals(s.getRemainingRenewals());
            renewBookResponse.setUserID(s.getUserId());

            if (days > 1 && (checkedOutRows.get().getRemainingRenewals() > 0)) {
                s.setId(checkedOutRows.get().getId());
                s.setCheckOutDate(checkedOutRows.get().getCheckOutDate());
                s.setExpectedReturnDate(newExpectedReturnDate);
                s.setRemainingRenewals(checkedOutRows.get().getRemainingRenewals() - 1);

                renewBookResponse.setRenewalResponse("Renewal Success");
            }
            else
            {
                renewBookResponse.setRenewalResponse("Renewal Not Allowed");
            }

            renewBook(rentBookList);
            renewBookResponseList.add(renewBookResponse);
        });

        renewResponseList.setRenewBookResponseList(renewBookResponseList);
        return renewResponseList;
    }

    private RentBookList getCheckOutDetails(Integer userID) {
        String url = "http://databaseservice/dbservice/checkout/{userid}";
        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("userid", userID);
        ResponseEntity<RentBookList> checkOutDetails = restTemplate.getForEntity(url, RentBookList.class, uriVariables);
        return checkOutDetails.getBody();
    }

    public String renewBook(RentBookList renewables) {
        String url = "http://databaseservice/dbservice/checkout";
        HttpEntity<RentBookList> request = new HttpEntity<>(renewables);
        restTemplate.postForObject(url, request, String.class);
       return "renewed";

    }
}
