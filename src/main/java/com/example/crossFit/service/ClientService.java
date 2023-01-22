package com.example.crossFit.service;

import com.example.crossFit.exceptions.EntityAlreadyIsRegisteredException;
import com.example.crossFit.exceptions.EntityNotFoundException;
import com.example.crossFit.model.entity.Client;
import com.example.crossFit.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND, "Клиент с таким ID не найден");
        }
        return client;
    }


    @Transactional(readOnly = true)
    public Client findByPhoneNumber(String phoneNumber) {
        Client client = clientRepo.findByPhoneNumber(phoneNumber);
        if (client == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Клиент с таким номером телефона не найден");
        }
        return client;
    }

    @Transactional
    public void registerVisitor(Client client) {
        if (clientRepo.findByPhoneNumber(client.getPhoneNumber()) != null) {
            throw new EntityAlreadyIsRegisteredException(HttpStatus.BAD_REQUEST,
                    "Клиент с таким номером телефона уже зарегистрирован");
        }
        if (clientRepo.findByEmail(client.getEmail()) != null) {
            throw new EntityAlreadyIsRegisteredException(HttpStatus.BAD_REQUEST,
                    "Клиент с такой электронной почтой уже зарегистрирован");
        }
        clientRepo.save(client);
    }

    @Transactional
    public void deleteClient(String phoneNumber) {
        Client client = clientRepo.findByPhoneNumber(phoneNumber);
        if (client == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Клиент с таким номером телефона не зарегистрирован в базе");
        }
        clientRepo.delete(client);
    }

    @Transactional
    public void payClient(String phoneNumber, BigDecimal money) {
        Client client = clientRepo.findByPhoneNumber(phoneNumber);
        if (client == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Клиент с таким номером телефона не найден в базе");
        }
        client.setBalance(client.getBalance().add(money));

        clientRepo.save(client);

    }


}
