package com.example.AlexFitness.controller;


import com.example.AlexFitness.entity.Client;
import com.example.AlexFitness.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
public class UtilController {
    public final UtilService utilService;

    @Autowired
    public UtilController(UtilService utilService) {
        this.utilService = utilService;
    }

    @PatchMapping("/updateClient")
    public void updateClient(Client client) {
        utilService.updateClient(client);
    }
}
