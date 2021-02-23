package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String username;

    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        String.format("Сообщение от %s. \n%s", subject, message);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }

    public void sendForConnect(String adminMail, String clientMail, String clientName, String messageText) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(adminMail);
        mailMessage.setSubject("Сообщение с формы связи");
        mailMessage.setText(String.format("Сообщение от %s (email - %s). \n%s", clientName, clientMail, messageText));
        javaMailSender.send(mailMessage);
    }
}
