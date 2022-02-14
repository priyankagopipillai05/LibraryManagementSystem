package com.library.management.bookRentingService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;



@Component
public class ReserveRequest {

    private Integer id;
    private Integer userId;
    private String bookId;
    private Date reservationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
