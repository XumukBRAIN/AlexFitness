package com.example.crossFit.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
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


}
