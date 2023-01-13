package com.example.AlexFitness.controller;


import com.example.AlexFitness.model.entity.RequestFit;
import com.example.AlexFitness.service.RequestFitService;
import com.example.AlexFitness.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/showRequestFit")
    public List<RequestFit> showRequestFit() {
        return requestFitService.findNotApprovedRequests();
    }

    @PatchMapping("/rejectRequestFit")
    public void rejectRequestFit(@RequestParam String phoneNumber) {
        requestFitService.rejectRequestFit(phoneNumber);
    }

    @PatchMapping("/updateClient")
    public void updateClient(@RequestParam String phoneNumber) {
        utilService.approve(phoneNumber);
    }


}
