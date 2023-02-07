package com.example.crossFit.model.dto;

import java.util.UUID;

public class ClientDTO {

    private UUID clientId;
    private String clientName;
    private String clientPhoneNumber;
    private Integer clientCoach;
    private Integer clientSubscriptionId;
    private String clientEmail;
    private String clientPassword;

    public ClientDTO(UUID clientId, String clientName, String clientPhoneNumber,
                     String clientEmail, Integer clientCoach, Integer clientSubscriptionId, String clientPassword) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientCoach = clientCoach;
        this.clientSubscriptionId = clientSubscriptionId;
        this.clientEmail = clientEmail;
        this.clientPassword = clientPassword;
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

    public Integer getClientCoach() {
        return clientCoach;
    }

    public void setClientCoach(Integer clientCoach) {
        this.clientCoach = clientCoach;
    }

    public Integer getClientSubscriptionId() {
        return clientSubscriptionId;
    }

    public void setClientSubscriptionId(Integer clientSubscriptionId) {
        this.clientSubscriptionId = clientSubscriptionId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }
}
