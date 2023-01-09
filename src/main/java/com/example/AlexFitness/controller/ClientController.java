package com.example.AlexFitness.controller;

import com.example.AlexFitness.entity.Client;
import com.example.AlexFitness.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Api("Контроллер для клиента")
@RestController
@RequestMapping("/visitor")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation("Метод для получения клиента по его айди")
    @GetMapping("/getOne/{id}")
    public Optional<Client> getVisitor(@PathVariable UUID id) {
        return clientService.getVisitor(id);
    }

    @ApiOperation("Метод для получения клиента по его номеру телефона")
    @GetMapping("/findByPhone/{phone}")
    public Client findByNumberPhone(@PathVariable String phone) {
        return clientService.findByPhoneNumber(phone);
    }

    @ApiOperation("Метод для добавления клиента")
    @PostMapping("/registerVisitor")
    public void registerVisitor(@RequestBody Client client) {
        clientService.registerVisitor(client);
    }

}
