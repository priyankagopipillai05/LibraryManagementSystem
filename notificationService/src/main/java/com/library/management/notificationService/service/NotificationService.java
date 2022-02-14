package com.library.management.notificationService.service;

import com.library.management.notificationService.model.MailContents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService  {
    Logger logger= LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private JavaMailSender emailSender;

    public String notificationMail(MailContents mailContents) {
logger.info("---------notification mail......-----");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailContents.getFrom());
        message.setTo(mailContents.getTo());
        message.setText(mailContents.getText());
        message.setSubject(mailContents.getSubject());
        emailSender.send(message);

    return "Mail Send";
}
}
