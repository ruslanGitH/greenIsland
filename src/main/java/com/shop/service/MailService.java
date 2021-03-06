package com.shop.service;

import com.shop.model.entity.ClientOrder;
import com.shop.model.entity.Orders;
import com.shop.model.entity.Product;
import com.shop.model.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailService {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private IProductRepo productRepo;

    public void orderMake(ClientOrder orders) {
        StringBuilder builder = new StringBuilder();
        List<Product> productList = new ArrayList<>();
        for (Orders order : orders.getOrders()) {
            Product product = productRepo.findById(order.getProduct().getId()).get();
            productList.add(product);
            builder.append(product.getName()).append(" - ").append(order.getCount()).append(" единицы.  Цена - ").append(product.getPrice()).append("\n");

        }
        try {
            mailSender.send(orders);
            mailSender.sendAdminMail(orders, builder.toString());
        } catch (Exception e) {
            System.out.println("Ошибка при отправке сообщений на почту");
        }
    }

    public void contactForm(String mail, String name, String text) {
        mailSender.sendForConnect(mail, name, text);
    }
}
