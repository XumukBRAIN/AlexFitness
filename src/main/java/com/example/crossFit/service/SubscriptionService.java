package com.example.crossFit.service;

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
        if (subscriptionRepo.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Абонементов не найдено!");
        }
        return subscriptionRepo.findAll();
    }

    @Transactional
    public String createSub(Subscription subscription) {
        subscriptionRepo.save(subscription);

        return "Абонемент успешно добавлен!";
    }

    @Transactional
    public String deleteSub(Integer id) {
        Optional<Subscription> subscription = subscriptionRepo.findById(id);
        if (subscription.isPresent()) {
            subscriptionRepo.deleteById(id);

            return "Абонемент успешно удален!";

        } else throw new ResourceNotFoundException("Абонемента с таким id: " + id + " не найдено!");
    }
}
