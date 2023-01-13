package com.example.AlexFitness.controller;

import com.example.AlexFitness.model.dto.SubscriptionDTO;
import com.example.AlexFitness.model.entity.Subscription;
import com.example.AlexFitness.model.mapStruct.SubscriptionMapper;
import com.example.AlexFitness.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subs")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, SubscriptionMapper subscriptionMapper) {
        this.subscriptionService = subscriptionService;
        this.subscriptionMapper = subscriptionMapper;
    }

    @GetMapping("/getAll")
    public List<Subscription> getAllSubs() {
        return subscriptionService.getAllSubs();
    }

    @PostMapping("/createSub")
    public void createSub(@RequestBody SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionMapper.toSubscription(subscriptionDTO);
        subscriptionService.createSub(subscription);
    }
}
