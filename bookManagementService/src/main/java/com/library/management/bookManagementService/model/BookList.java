package com.library.management.bookManagementService.model;

import java.util.List;

public class BookList {

        List<BookDetailsRequest> bookDetailsList;

        public List<BookDetailsRequest> getBookDetailsList() {
            return bookDetailsList;
        }

        public void setBookDetailsList(List<BookDetailsRequest> bookDetailsList) {

            this.bookDetailsList = bookDetailsList;
        }

}
