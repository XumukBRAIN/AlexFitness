package com.example.AlexFitness.service;

import com.example.AlexFitness.entity.Client;
import com.example.AlexFitness.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Optional<Client> getVisitor(UUID id) {
        return clientRepo.findById(id);
    }

    public Client findByPhoneNumber(String phone) {
        return clientRepo.findByPhoneNumber(phone);
    }

    @Transactional
    public void registerVisitor(Client client) {
        clientRepo.save(client);
    }


}
