package com.library.management.databaseService.checkout.entity;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Date;

@Entity(name="bookcheckout")
public class Checkout {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="user_id")
    private Integer userId;
    @Column(name="book_id")
    private String bookId;
    @Column(name="checkout_date")
    private Date checkOutDate;
    @Column(name="expected_returndate")
    private Date expectedReturnDate;
    @Column(name="remaining_renewals")
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
