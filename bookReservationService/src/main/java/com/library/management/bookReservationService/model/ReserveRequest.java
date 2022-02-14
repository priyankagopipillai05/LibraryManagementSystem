package com.library.management.bookReservationService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Setter
@Getter
@Component
public class ReserveRequest {

    private Integer id;
    private Integer userId;
    private String bookId;
    private Date reservationDate;
}
