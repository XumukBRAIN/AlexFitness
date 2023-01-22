package com.example.crossFit.controller;


import com.example.crossFit.model.dto.MessageDTO;
import com.example.crossFit.service.UtilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ApiOperation("Метод единовременной массовой рассылки")
    @PostMapping("/sendToAll")
    public void sendToAll(@RequestBody MessageDTO messageDTO) {
        utilService.sendToAll(messageDTO.getTitle(), messageDTO.getText());
    }

}
