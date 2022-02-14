package com.library.management.databaseService.user.controller;

import com.library.management.databaseService.user.service.UserService;
import com.library.management.databaseService.user.model.UserList;
import com.library.management.databaseService.user.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/dbservice/user")
    public UserList showUser(){
        List<UserRequest> userRequestList=userService.displayUser();

        UserList obj=new UserList();
        obj.setUserRequestList(userRequestList);

        return obj;
    }

    @GetMapping("/dbservice/user/id/{userid}")
    public UserRequest getUserWithId(@PathVariable Integer userid){

        return userService.getUserWithId(userid);
    }

    @PostMapping("/dbservice/user")
    public Integer addUser(@RequestBody UserRequest userRequest){

        return userService.createUser(userRequest);
    }

    @PatchMapping("/dbservice/user/{id}")
    public Integer updateUser(@RequestBody UserRequest userRequest,@PathVariable Integer id ){
        return userService.updateUser(userRequest,id);
    }

    @DeleteMapping("/dbservice/user/{id}")
    public Integer deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }

}
