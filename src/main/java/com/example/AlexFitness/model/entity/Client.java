package com.example.AlexFitness.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "client")
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


    public Client() {
    }


    public Client(String name, String phoneNumber, Integer coach, Integer subscriptionId, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.coach = coach;
        this.subscriptionId = subscriptionId;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getCoach() {
        return coach;
    }

    public void setCoach(Integer coach) {
        this.coach = coach;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
