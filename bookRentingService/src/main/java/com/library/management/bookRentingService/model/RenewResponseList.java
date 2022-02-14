package com.library.management.bookRentingService.model;

import java.util.List;

public class RenewResponseList {

    private List<RenewBookResponse> renewBookResponseList;

    public List<RenewBookResponse> getRenewBookResponseList() {
        return renewBookResponseList;
    }

    public void setRenewBookResponseList(List<RenewBookResponse> renewBookResponseList) {
        this.renewBookResponseList = renewBookResponseList;
    }
}
