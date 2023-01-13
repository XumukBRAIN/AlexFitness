package com.example.AlexFitness.controller;


import com.example.AlexFitness.model.entity.RequestFit;
import com.example.AlexFitness.service.RequestFitService;
import com.example.AlexFitness.service.UtilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Контроллер утилиты")
@RestController
@RequestMapping("/util")
public class UtilController {
    private final UtilService utilService;
    private final RequestFitService requestFitService;

    @Autowired
    public UtilController(UtilService utilService, RequestFitService requestFitService) {
        this.utilService = utilService;
        this.requestFitService = requestFitService;
    }

    @ApiOperation("Метод для поиска всех созданных заявок на абонемент и тренера")
    @GetMapping("/showRequestFit")
    public List<RequestFit> showRequestFit() {
        return requestFitService.findNotApprovedRequests();
    }

    @ApiOperation("Метод для отклонения заявки на абонемент")
    @PatchMapping("/rejectRequestFit")
    public void rejectRequestFit(@RequestParam String phoneNumber) {
        requestFitService.rejectRequestFit(phoneNumber);
    }

    @ApiOperation("Метод для одобрения заявки на абонемент")
    @PatchMapping("/approveRequestFit")
    public void approveRequestFit(@RequestParam String phoneNumber) {
        utilService.approve(phoneNumber);
    }


}
