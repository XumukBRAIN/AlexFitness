package com.example.AlexFitness.service;

import com.example.AlexFitness.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilService {
    private final RequestFitService requestFitService;
    private final ClientRepo clientRepo;

    @Autowired
    public UtilService(RequestFitService requestFitService, ClientRepo clientRepo) {
        this.requestFitService = requestFitService;
        this.clientRepo = clientRepo;
    }

    public void sendToAll(String subject, String text) {
        clientRepo.findAll().stream().filter(client -> client.getEmail() != null).forEach(client ->
                requestFitService.sendMessage(client.getEmail(), subject, text)
        );

    }
}

