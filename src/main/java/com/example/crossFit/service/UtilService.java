package com.example.crossFit.service;

import com.example.crossFit.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilService {
    private final RequestFitService requestFitService;
    private final ClientRepo clientRepo;

    @Autowired
    public UtilService(RequestFitService requestFitService, ClientRepo clientRepo) {
        this.requestFitService = requestFitService;
        this.clientRepo = clientRepo;
    }

    @Transactional
    public void sendToAll(String subject, String text) {
        clientRepo.findAll().stream().filter(client -> client.getEmail() != null).forEach(client ->
                requestFitService.sendMessage(client.getEmail(), subject, text)
        );

    }
}

