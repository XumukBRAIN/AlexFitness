package com.example.crossFit.service;

import com.example.crossFit.exceptions.ResourceAlreadyIsRegistered;
import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Subscription;
import com.example.crossFit.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public String createSub(Subscription subscription) {
        if (subscriptionRepo.findById(subscription.getId()).isPresent()) {
            throw new ResourceAlreadyIsRegistered("Абонемент с таким id: " + subscription.getId() + " уже существует в базе!");
        }
        subscriptionRepo.save(subscription);

        return "Абонемент добавлен!";
    }

    @Transactional
    public String deleteSub(Integer id) {
        Optional<Subscription> subscription = subscriptionRepo.findById(id);
        if (subscription.isPresent()) {
            subscriptionRepo.deleteById(id);
            return "Абонемент удален из базы!";

        } else throw new ResourceNotFoundException("Абонемент с таким id: " + id + " не найден в базе!");
    }
}
