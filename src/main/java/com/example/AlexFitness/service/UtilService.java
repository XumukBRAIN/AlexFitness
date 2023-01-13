package com.example.AlexFitness.service;

import com.example.AlexFitness.model.entity.Client;
import com.example.AlexFitness.model.entity.RequestFit;
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
    public void approve(String phoneNumber) {
        RequestFit requestFit = requestFitService.findByPhoneNumber(phoneNumber);
        if (requestFit == null) {
            throw new RuntimeException("Заявка с таким номером телефона не найдена");
        }
        Client client1 = clientService.findByPhoneNumber(phoneNumber);
        if (client1 == null) {
            throw new RuntimeException("Клиент с таким номером телефона не найден");
        }
        requestFit.setApproved(true);
        client1.setCoach(requestFit.getCoachId());
        client1.setSubscriptionId(requestFit.getSubId());

        clientService.updateClient(client1);
        requestFitService.approveRequestFit(requestFit);
    }
}
