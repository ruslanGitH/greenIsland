package com.shop.model.entity;

import com.shop.model.enums.ClientOrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "client_order")
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double price;
    private LocalDateTime date;
    private String address;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    private ClientOrderStatus status;

    @OneToMany(mappedBy = "clientOrder")
    private List<Orders> orders = new ArrayList<>();

}
