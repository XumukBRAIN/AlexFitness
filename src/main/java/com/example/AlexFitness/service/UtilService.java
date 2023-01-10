package com.example.AlexFitness.service;

import com.example.AlexFitness.entity.Client;
import com.example.AlexFitness.entity.RequestFit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UtilService {
    public final ClientService clientService;
    public final RequestFitService requestFitService;

    @Autowired
    public UtilService(ClientService clientService, RequestFitService requestFitService) {
        this.clientService = clientService;
        this.requestFitService = requestFitService;
    }

    @Transactional
    public void updateClient(Client client) {
        Client client1 = clientService.clientRepo.findByPhoneNumber(client.getPhoneNumber());
        RequestFit requestFit = requestFitService.requestFitRepo.findByPhoneNumber(client.getPhoneNumber());

        client1.setCoach(requestFit.getCoachId());
        client1.setSubscriptionId(requestFit.getSubId());
        //TODO
    }
}
