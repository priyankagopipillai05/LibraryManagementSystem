package com.library.management.userRegistrationService.model;

import com.library.management.userRegistrationService.model.User;

import java.util.List;

public class UserList {

    List<User> userRequestList;

    public List<User> getUserRequestList() {
        return userRequestList;
    }

    public void setUserRequestList(List<User> userRequestList) {
        this.userRequestList = userRequestList;
    }
}
