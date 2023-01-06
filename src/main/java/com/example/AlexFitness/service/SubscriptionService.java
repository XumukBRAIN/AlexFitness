package com.example.AlexFitness.service;

import com.example.AlexFitness.entity.Subscription;
import com.example.AlexFitness.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepo subscriptionRepo;

    @Autowired
    public SubscriptionService(SubscriptionRepo subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
    }

    public List<Subscription> getAllSubs() {
        return subscriptionRepo.findAll();
    }
}
