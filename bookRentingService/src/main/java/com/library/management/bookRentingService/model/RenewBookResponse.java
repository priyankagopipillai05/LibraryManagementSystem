package com.library.management.bookRentingService.model;


import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RenewBookResponse {

    Integer userID;
    String bookID;
    Date expectedReturnDate;
    Integer numberOfRenewals;
    String renewalResponse;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Integer getNumberOfRenewals() {
        return numberOfRenewals;
    }

    public void setNumberOfRenewals(Integer numberOfRenewals) {
        this.numberOfRenewals = numberOfRenewals;
    }

    public String getRenewalResponse() {
        return renewalResponse;
    }

    public void setRenewalResponse(String renewalResponse) {
        this.renewalResponse = renewalResponse;
    }
}
