package com.shop.service;

import com.shop.model.entity.ClientOrder;
import com.shop.model.entity.Orders;
import com.shop.model.entity.Product;
import com.shop.model.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private IProductRepo productRepo;

    public void orderMake(ClientOrder orders) {
        StringBuilder builder = new StringBuilder();
        for (Orders order : orders.getOrders()) {
            Product product = productRepo.findById(order.getProduct().getId()).get();
            builder.append(product.getName()).append(" - ").append(order.getCount()).append(" единицы.  Цена - ").append(product.getPrice()).append("\n");
        }

        String message = String.format("Добрый день!\n Ваш заказ принят!\n Ваш заказ: \n %s. \n  Ожидайте звонка на указанный вами номер - %s для подтверждения заказа.",
                builder.toString(), orders.getClient().getPhoneNumber());
        mailSender.send(orders.getClient().getEmail(), "Зелёный остров", message);
    }

    public void contactForm(String mail, String name, String text) {
        mailSender.sendForConnect(mail, name, text);
    }
}
