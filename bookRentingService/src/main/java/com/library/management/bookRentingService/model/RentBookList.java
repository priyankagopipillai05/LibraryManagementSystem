package com.library.management.bookRentingService.model;

import java.util.List;

public class RentBookList {

    List<RentBook> checkOutList;

    public List<RentBook> getCheckOutList() {
        return checkOutList;
    }

    public void setCheckOutList(List<RentBook> checkOutList) {
        this.checkOutList = checkOutList;
    }
}
