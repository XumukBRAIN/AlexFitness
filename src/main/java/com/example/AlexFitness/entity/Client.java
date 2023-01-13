package com.example.AlexFitness.entity;

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

    public Client() {
    }


    public Client(String name, String phoneNumber, int coach, int subscriptionId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.coach = coach;
        this.subscriptionId = subscriptionId;
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

    public int getCoach() {
        return coach;
    }

    public void setCoach(int coach) {
        this.coach = coach;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
