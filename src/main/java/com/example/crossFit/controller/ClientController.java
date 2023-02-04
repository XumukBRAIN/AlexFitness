package com.example.crossFit.controller;

import com.example.crossFit.config.SwaggerConfig;
import com.example.crossFit.model.dto.ClientDTO;
import com.example.crossFit.model.entity.Client;
import com.example.crossFit.model.mapStruct.ClientMapper;
import com.example.crossFit.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/client")
@Api(tags = SwaggerConfig.CLIENT_TAG)
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
    @GetMapping("/findByPhone")
    public Client findByNumberPhone(@RequestParam String phoneNumber) {
        return clientService.findByPhoneNumber(phoneNumber);
    }

    @ApiOperation("Метод для добавления клиента")
    @PostMapping("/register")
    public void registerVisitor(@RequestBody ClientDTO clientDTO) {
        clientService.registerVisitor(clientMapper.toClient(clientDTO));
    }

    @ApiOperation("Метод для удаления клиента из базы данных")
    @DeleteMapping("/deleteClient")
    public void deleteClient(@RequestParam String phoneNumber) {
        clientService.deleteClient(phoneNumber);
    }

    @ApiOperation("Метод для пополнения личного кабинета клиента")
    @PatchMapping("/pay")
    public void payClient(@RequestParam String phoneNumber, @RequestParam BigDecimal money) {
        clientService.payClient(phoneNumber, money);
    }

    @PostMapping("/orders/create")
    public void createOrder(@RequestParam String phoneNumber,
                            @RequestParam Integer id, @RequestParam String title) {
        clientService.createMyOrders(phoneNumber, id, title);
    }

}
