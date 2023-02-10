package com.example.crossFit.controller;


import com.example.crossFit.config.SwaggerConfig;
import com.example.crossFit.model.dto.MessageDTO;
import com.example.crossFit.service.UtilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/util")
@CrossOrigin
@Api(tags = SwaggerConfig.UTIL_TAG)
public class UtilController {

    private final UtilService utilService;

    @Autowired
    public UtilController(UtilService utilService) {
        this.utilService = utilService;
    }

    /**
     * Автономный метод массовой рассылки
     *
     * @param messageDTO
     */
    @ApiOperation("Метод единовременной массовой рассылки")
    @PostMapping("/sendToAll")
    public void sendToAll(@RequestBody MessageDTO messageDTO) {
        utilService.sendToAll(messageDTO.getTitle(), messageDTO.getText());
    }

}
