package com.shop.model.entity;

import com.shop.model.enums.Role;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastName;
    private String firstName;
    @NotNull
    private String phoneNumber;
    private String email;

    private String username;
    private String password;
    private boolean active;

    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "client"))
    @Enumerated(EnumType.STRING)
    private Role role;

}
