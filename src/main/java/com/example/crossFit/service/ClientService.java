package com.example.crossFit.service;

import com.example.crossFit.exceptions.ClientIsRegisteredException;
import com.example.crossFit.model.entity.Client;
import com.example.crossFit.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    public void registerVisitor(Client client) throws ClientIsRegisteredException {
        if (clientRepo.findByPhoneNumber(client.getPhoneNumber()) != null) {
            throw new ClientIsRegisteredException("Клиент с номером телефона: "
                    + client.getPhoneNumber() + " уже зарегистрирован");
        }
        if (clientRepo.findByEmail(client.getEmail()) != null) {
            throw new ClientIsRegisteredException("Клиент с email: "
                    + client.getEmail() + " уже зарегистрирован");
        }
        clientRepo.save(client);
    }

    @Transactional
    public void deleteClient(String phoneNumber) {
        Client client = clientRepo.findByPhoneNumber(phoneNumber);
        clientRepo.delete(client);
    }

    @Transactional
    public void payClient(String phoneNumber, BigDecimal money) {
        Client client = clientRepo.findByPhoneNumber(phoneNumber);
        client.setBalance(client.getBalance().add(money));

        clientRepo.save(client);


    }


}
