package com.shop.service;

import com.shop.model.entity.ClientOrder;
import com.shop.model.entity.Orders;
import com.shop.model.entity.Product;
import com.shop.model.enums.Role;
import com.shop.model.repository.IClientRepo;
import com.shop.model.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private IClientRepo clientRepo;
    @Autowired
    private IProductRepo productRepo;

    public void orderMake(ClientOrder orders) {
        String clientMessage = String.format("Добрый день! Ваш заказ принят! Ожидайте звонка на указанный вами номер - %s для подтверждения заказа.", orders.getClient().getPhoneNumber());
        mailSender.send(orders.getClient().getEmail(), "Зелёный остров", clientMessage);
        StringBuilder builder = new StringBuilder();
        for (Orders order : orders.getOrders()) {
            Product product = productRepo.findById(order.getProduct().getId()).get();
            builder.append(product.getName()).append(" - ").append(order.getCount()).append(" еденицы.  Цена - ").append(product.getPrice()).append("\n");
        }
        String bossMassage = String.format("Новый заказ. Номер телефон  клиента %s. \n Заказ: \n %s \n", orders.getClient().getPhoneNumber(), builder.toString());
        mailSender.send(clientRepo.findByRole(Role.ADMIN).getEmail(), "Новый заказ", bossMassage);
    }

    public void contactForm(String mail, String name, String text) {
        mailSender.sendForConnect(clientRepo.findByRole(Role.ADMIN).getEmail(), mail, name, text);
    }
}
