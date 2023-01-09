package com.example.AlexFitness.controller;

import com.example.AlexFitness.entity.Client;
import com.example.AlexFitness.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/visitor")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/getOne/{id}")
    public Optional<Client> getVisitor(@PathVariable UUID id) {
        return clientService.getVisitor(id);
    }

    @GetMapping("/findByPhone/{phone}")
    public Client findByNumberPhone(@PathVariable String phone) {
        return clientService.findByPhoneNumber(phone);
    }

    @PostMapping("/registerVisitor")
    public void registerVisitor(@RequestBody Client client) {
        clientService.registerVisitor(client);
    }

}
