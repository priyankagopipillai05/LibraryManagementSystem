package com.library.management.notificationService.controller;

import com.library.management.notificationService.model.MailContents;
import com.library.management.notificationService.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("/notificationservice/reservedbook/pickup/mail")
    public String notificationMail(@RequestBody MailContents mailContents){
        return notificationService.notificationMail(mailContents);

    }
}
