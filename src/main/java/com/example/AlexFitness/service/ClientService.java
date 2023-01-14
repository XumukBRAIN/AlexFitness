package com.example.AlexFitness.service;

import com.example.AlexFitness.model.entity.Client;
import com.example.AlexFitness.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }


    public Client getVisitor(UUID id) {
        Client client = clientRepo.findById(id);
        if (client == null) {
            throw new RuntimeException("Клиент с таким ID не найден");
        }
        return client;
    }


    public Client findByPhoneNumber(String phoneNumber) {
        return clientRepo.findByPhoneNumber(phoneNumber);
    }


    public void registerVisitor(Client client) {
        clientRepo.save(client);
    }

    public void deleteClient(String phoneNumber) {
        Client client = clientRepo.findByPhoneNumber(phoneNumber);
        clientRepo.delete(client);
    }

    public void payClient(String phoneNumber, BigDecimal money) {
        Client client = clientRepo.findByPhoneNumber(phoneNumber);
        client.setBalance(client.getBalance().add(money));

        clientRepo.save(client);


    }


}
