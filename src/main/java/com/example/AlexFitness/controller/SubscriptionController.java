package com.example.AlexFitness.controller;

import com.example.AlexFitness.model.dto.SubscriptionDTO;
import com.example.AlexFitness.model.entity.Subscription;
import com.example.AlexFitness.model.mapStruct.SubscriptionMapper;
import com.example.AlexFitness.service.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Контроллер для абонемента")
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

    @ApiOperation("Метод для отображения всех видов абонемента")
    @GetMapping("/getAll")
    public List<Subscription> getAllSubs() {
        return subscriptionService.getAllSubs();
    }

    @ApiOperation("Метод для добавления нового вида абонемента")
    @PostMapping("/createSub")
    public void createSub(@RequestBody SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionMapper.toSubscription(subscriptionDTO);
        subscriptionService.createSub(subscription);
    }
}
