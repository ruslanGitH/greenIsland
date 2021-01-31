package com.shop.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int count;

    @ManyToOne
    @JoinColumn(name = "client_order_id")
    private ClientOrder clientOrder;

    @OneToOne
    protected Product product;

}
