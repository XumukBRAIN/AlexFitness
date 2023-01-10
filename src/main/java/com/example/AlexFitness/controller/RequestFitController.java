package com.example.AlexFitness.controller;


import com.example.AlexFitness.entity.RequestFit;
import com.example.AlexFitness.service.RequestFitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientRequest")
public class RequestFitController {
    private final RequestFitService requestFitService;

    @Autowired
    public RequestFitController(RequestFitService requestFitService) {
        this.requestFitService = requestFitService;
    }

    @PostMapping("/createRequet")
    public void createRequest(RequestFit requestFit) {
        requestFitService.createRequest(requestFit);
    }
}
