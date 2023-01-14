package com.example.AlexFitness.controller;


import com.example.AlexFitness.service.UtilService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Контроллер для утилит")
@RestController
@RequestMapping("/util")
public class UtilController {
    private final UtilService utilService;

    @Autowired
    public UtilController(UtilService utilService) {
        this.utilService = utilService;
    }

    @PostMapping("/sendToAll")
    public void sendToAll(String subject, String text) {
        utilService.sendToAll(subject, text);
    }


}
