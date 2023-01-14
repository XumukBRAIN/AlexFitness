package com.example.AlexFitness.controller;

import com.example.AlexFitness.model.dto.ClientDTO;
import com.example.AlexFitness.model.entity.Client;
import com.example.AlexFitness.model.mapStruct.ClientMapper;
import com.example.AlexFitness.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api("Контроллер для клиента")
@RestController
@RequestMapping("/visitor")
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @ApiOperation("Метод для получения клиента по его айди")
    @GetMapping("/getOne")
    public ClientDTO getClient(@RequestParam UUID id) {
        Client client = clientService.getVisitor(id);
        return clientMapper.toClientDTO(client);
    }

    @ApiOperation("Метод для получения клиента по его номеру телефона")
    @GetMapping("/findByPhone/{phone}")
    public Client findByNumberPhone(@PathVariable String phone) {
        return clientService.findByPhoneNumber(phone);
    }

    @ApiOperation("Метод для добавления клиента")
    @PostMapping("/registerVisitor")
    public void registerVisitor(@RequestBody ClientDTO clientDTO) {
        clientService.registerVisitor(clientMapper.toClient(clientDTO));
    }

}
