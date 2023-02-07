package com.example.crossFit.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    @Size(min = 11, max = 11, message = "Номер начинается с цифры 8. Ожидается 11 цифр")
    private String phoneNumber;
    @JoinColumn(name = "coach")
    private Integer coach;
    @JoinColumn(name = "subscription_id")
    private Integer subscriptionId;
    private String email;
    @JsonIgnore
    private String password;
    private BigDecimal balance;
    @JsonIgnore
    @OneToMany(mappedBy = "clientId")
    private List<Orders> orders;
    @JsonIgnore
    private String role;


    public Client(String name, String phoneNumber, Integer coach, Integer subscriptionId,
                  List<Orders> orders, String email, BigDecimal balance, String password, String role) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.coach = coach;
        this.subscriptionId = subscriptionId;
        this.email = email;
        this.balance = balance;
        this.orders = orders;
        this.password = password;
        this.role = role;
    }


}
