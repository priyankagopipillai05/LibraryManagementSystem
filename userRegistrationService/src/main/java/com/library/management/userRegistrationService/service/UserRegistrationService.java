package com.library.management.userRegistrationService.service;

import com.library.management.userRegistrationService.model.UserList;
import com.library.management.userRegistrationService.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRegistrationService {

    @Autowired
    private RestTemplate restTemplate;

    Logger logger= LoggerFactory.getLogger(UserRegistrationService.class);
    public UserList displayUser() {
        String url="http://databaseservice/dbservice/user";

        ResponseEntity<UserList> responseEntity=restTemplate.getForEntity(url,UserList.class);
        return responseEntity.getBody();
        //return restTemplate.getForObject(url,UserList.class);
    }


    public Integer createUser(User user) {

        logger.info("Create users here");
        String url="http://databaseservice/dbservice/user";
        HttpEntity<User> request=new HttpEntity<>(user);

        //Other way of calling
       // ResponseEntity<Integer> responseEntity=
        // restTemplate.exchange(url,HttpMethod.POST,request,Integer.class);


        return restTemplate.postForObject(url,request,Integer.class);
    }

    public Integer updateUser(User user, Integer id) {

        String url="http://databaseservice/dbservice/user/{id}";

        Map<String,String> params = new HashMap<>();
        params.put("id",String.valueOf(id));

        HttpEntity<User> request=new HttpEntity<>(user);

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        ResponseEntity<Integer> responseEntity=
                restTemplate.exchange(url, HttpMethod.PATCH,request,Integer.class,params);
        // Other way of calling
        // restTemplate.patchForObject(url,request,Integer.class,params);

        return responseEntity.getBody();
    }

    public String deleteUser(Integer id) {

        String url="http://databaseservice/dbservice/user/{id}";
        Map<String,String> params = new HashMap<>();
        params.put("id",String.valueOf(id));

        //Other way of calling
        /* Map<String,Integer> params = new HashMap<>();
           params.put("id",id);
            ResponseEntity<Integer> responseEntity =
                restTemplate.exchange(url,HttpMethod.DELETE,Integer.class,params);
         */

        restTemplate.delete(url,params);

        return "Deletion Successfully Completed";
    }


}
