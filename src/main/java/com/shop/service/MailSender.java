package com.shop.service;

import com.shop.model.entity.ClientOrder;
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
        mailMessage.setText(String.format("Сообщение от %s. \n%s", subject, message));
        javaMailSender.send(mailMessage);
    }
    public void sendAdminMail(ClientOrder order , String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(username);
        mailMessage.setSubject("Новый заказ!");

        mailMessage.setText(String.format("Поступил новый заказ. Информация: " +
                "\nИмя клиента: %s " +
                "\nНомер телефона: %s" +
                "\nПочта: %s " +
                "\nЗаказ: %s", order.getClient().getFirstName(), order.getClient().getPhoneNumber(),
                order.getClient().getEmail(), message));
        javaMailSender.send(mailMessage);

    }


    public void sendForConnect(String clientMail, String clientName, String messageText) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo("info@green-land-store.ru");
        mailMessage.setSubject("Сообщение с формы связи");
        mailMessage.setText(String.format("Сообщение от %s (email - %s). \n%s", clientName, clientMail, messageText));
        javaMailSender.send(mailMessage);
    }
}
