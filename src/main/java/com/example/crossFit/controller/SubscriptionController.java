package com.example.crossFit.controller;

import com.example.crossFit.config.SwaggerConfig;
import com.example.crossFit.model.dto.SubscriptionDTO;
import com.example.crossFit.model.entity.Subscription;
import com.example.crossFit.model.mapStruct.SubscriptionMapper;
import com.example.crossFit.response.SuccessResponse;
import com.example.crossFit.service.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subs")
@CrossOrigin
@Api(tags = SwaggerConfig.SUBSCRIPTION_TAG)
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
    public ResponseEntity<List<SubscriptionDTO>> getAllSubs() {
        List<Subscription> subscriptions = subscriptionService.getAllSubs();
        return ResponseEntity.ok(subscriptionMapper.toListSubscriptionDTO(subscriptions));
    }

    @ApiOperation("Метод для добавления нового вида абонемента")
    @PostMapping("/createSub")
    public ResponseEntity<SuccessResponse> createSub(@RequestBody SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionMapper.toSubscription(subscriptionDTO);
        return ResponseEntity.ok(subscriptionService.createSub(subscription));
    }

    @ApiOperation("Метод для удаления абонемента по ID")
    @DeleteMapping("/deleteSub")
    public ResponseEntity<SuccessResponse> deleteSub(@RequestParam Integer id) {
        return ResponseEntity.ok(subscriptionService.deleteSub(id));
    }

}
