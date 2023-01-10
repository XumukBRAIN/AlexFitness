package com.example.AlexFitness.service;

import com.example.AlexFitness.entity.Client;
import com.example.AlexFitness.entity.RequestFit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UtilService {
    private final ClientService clientService;
    private final RequestFitService requestFitService;

    @Autowired
    public UtilService(ClientService clientService, RequestFitService requestFitService) {
        this.clientService = clientService;
        this.requestFitService = requestFitService;
    }

    @Transactional
    public void updateClient(String phoneNumber) {
        Client client1 = clientService.findByPhoneNumber(phoneNumber);
        RequestFit requestFit = requestFitService.findByPhoneNumber(phoneNumber);

        client1.setCoach(requestFit.getCoachId());
        client1.setSubscriptionId(requestFit.getSubId());

        clientService.updateClient(client1);
    }
}
