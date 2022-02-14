package com.library.management.databaseService.checkout.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CheckOutRequest {

    private Long id;
    private Integer userId;
    private String bookId;
    private Date checkOutDate;
    private Date expectedReturnDate;
    private Integer remainingRenewals;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Integer getRemainingRenewals() {
        return remainingRenewals;
    }

    public void setRemainingRenewals(Integer remainingRenewals) {
        this.remainingRenewals = remainingRenewals;
    }
}
