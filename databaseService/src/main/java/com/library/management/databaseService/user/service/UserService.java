package com.library.management.databaseService.user.service;

import com.library.management.databaseService.user.repository.UserRepository;
import com.library.management.databaseService.user.entity.User;
import com.library.management.databaseService.user.model.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public Integer createUser(UserRequest userRequest) {

        User newUser = new User();
       // newUser.setId(userRequest.getId());
        newUser.setName(userRequest.getName());
        newUser.setAddress(userRequest.getAddress());
        newUser.setEmail_id(userRequest.getEmail_id());
        newUser.setVerification_id(userRequest.getVerification_id());
        newUser.setPhone_number(userRequest.getPhone_number());

       userRepository.save(newUser);

        return newUser.getId();

    }

    public Integer updateUser(UserRequest userRequest, Integer id) {

        if (userRepository.existsById(id)) {
            User userObj = userRepository.findById(id).get();
            if(userRequest.getName()!=null) {
                userObj.setName(userRequest.getName());
            }
            if(userRequest.getAddress()!=null) {
                userObj.setAddress(userRequest.getAddress());
            }
            if(userRequest.getEmail_id()!=null) {
            userObj.setEmail_id(userRequest.getEmail_id());}

            if(userRequest.getVerification_id()!=null) {
            userObj.setVerification_id(userRequest.getVerification_id());}

            if(userRequest.getPhone_number()!=null) {
            userObj.setPhone_number(userRequest.getPhone_number());}


            userRepository.save(userObj);


            return userObj.getId();
        }


        return null;
    }


    public Integer deleteUser(Integer id) {
        if(userRepository.existsById(id)){
            User userObj=userRepository.findById(id).get();
            userRepository.delete(userObj);
           return userObj.getId();
        }
        return null;
    }

    public List<UserRequest> displayUser() {
        List<User> userList=userRepository.findAll();

        List<UserRequest> userRequestList=new ArrayList<>();

        userList.stream().forEach(s->{
            UserRequest userRequest=new UserRequest();
            userRequest.setId(s.getId());
            userRequest.setName(s.getName());
            userRequest.setAddress(s.getAddress());
            userRequest.setEmail_id(s.getEmail_id());
            userRequest.setPhone_number(s.getPhone_number());
            userRequest.setVerification_id(s.getVerification_id());
            userRequestList.add(userRequest);
        });
        return userRequestList;

        }

    public UserRequest getUserWithId(Integer userid) {
        logger.info("User id herre is {}",userid);
        User user= userRepository.getById(userid);
        UserRequest userRequest=new UserRequest();
        userRequest.setId(user.getId());
        userRequest.setName(user.getName());
        userRequest.setAddress(user.getAddress());
        userRequest.setEmail_id(user.getEmail_id());
        userRequest.setPhone_number(user.getPhone_number());
        userRequest.setVerification_id(user.getVerification_id());

        return userRequest;
    }
}
