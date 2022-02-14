package com.library.management.databaseService.user.model;

import com.library.management.databaseService.user.model.UserRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserList {

    List<UserRequest> userRequestList;

    public List<UserRequest> getUserRequestList() {
        return userRequestList;
    }

    public void setUserRequestList(List<UserRequest> userRequestList) {
        this.userRequestList = userRequestList;
    }


}
