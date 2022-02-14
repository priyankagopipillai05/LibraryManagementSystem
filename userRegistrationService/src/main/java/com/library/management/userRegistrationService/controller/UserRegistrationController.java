package com.library.management.userRegistrationService.controller;

import com.library.management.userRegistrationService.model.UserList;
import com.library.management.userRegistrationService.model.User;
import com.library.management.userRegistrationService.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserRegistrationController {



        @Autowired
        UserRegistrationService userRegistrationService;

        @GetMapping("/user-registration/user")
        public UserList showUser(){
            UserList userList = userRegistrationService.displayUser();
            return userList;
        }

        @PostMapping("/user-registration/user")
        public Integer addUser(@Valid @RequestBody User user) throws Exception{

            return userRegistrationService.createUser(user);
        }

        @PatchMapping("/user-registration/user/{id}")
        public Integer updateUser(@RequestBody User user,@PathVariable Integer id ){
            return userRegistrationService.updateUser(user,id);
        }

        @DeleteMapping("/user-registration/user/{id}")
        public String deleteUser(@PathVariable Integer id){
            return userRegistrationService.deleteUser(id);
        }


    }
