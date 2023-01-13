package com.example.AlexFitness.controller;


import com.example.AlexFitness.model.entity.RequestFit;
import com.example.AlexFitness.service.RequestFitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientRequestFit")
public class RequestFitController {
    private final RequestFitService requestFitService;

    @Autowired
    public RequestFitController(RequestFitService requestFitService) {
        this.requestFitService = requestFitService;
    }

    @PostMapping("/createRequestFit")
    public void createRequest(@RequestBody RequestFit requestFit) {
        requestFitService.createRequest(requestFit);
    }

    @GetMapping("/notApproved")
    public List<RequestFit> findNotApprovedRequests() {
        return requestFitService.findNotApprovedRequests();
    }

}
