package com.example.AlexFitness.service;

import com.example.AlexFitness.model.entity.Subscription;
import com.example.AlexFitness.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubscriptionService {
    private final SubscriptionRepo subscriptionRepo;

    @Autowired
    public SubscriptionService(SubscriptionRepo subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
    }

    @Transactional(readOnly = true)
    public List<Subscription> getAllSubs() {
        return subscriptionRepo.findAll();
    }

    @Transactional
    public void createSub(Subscription subscription) {
        subscriptionRepo.save(subscription);
    }
}
