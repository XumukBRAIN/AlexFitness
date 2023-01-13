package com.example.AlexFitness.model.dto;

import java.util.UUID;


public class ClientDTO {

    private UUID clientId;
    private String clientName;
    private String clientPhoneNumber;
    private Integer coach;
    private Integer subscriptionId;

    public ClientDTO(UUID clientId, String clientName, String clientPhoneNumber, Integer coach, Integer subscriptionId) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.coach = coach;
        this.subscriptionId = subscriptionId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
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
}
