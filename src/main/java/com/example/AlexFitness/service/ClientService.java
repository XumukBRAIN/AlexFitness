package com.example.AlexFitness.service;

import com.example.AlexFitness.model.entity.Client;
import com.example.AlexFitness.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Transactional(readOnly = true)
    public Client getVisitor(UUID id) {
        Client client = clientRepo.findById(id);
        if (client == null) {
            throw new RuntimeException("Клиент с таким ID не найден");
        }
        return client;
    }

    @Transactional(readOnly = true)
    public Client findByPhoneNumber(String phoneNumber) {
        return clientRepo.findByPhoneNumber(phoneNumber);
    }

    @Transactional
    public void registerVisitor(Client client) {
        clientRepo.save(client);
    }


}
