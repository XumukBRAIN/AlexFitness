package com.example.crossFit.service;

import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Subscription;
import com.example.crossFit.repository.SubscriptionRepo;
import com.example.crossFit.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public SuccessResponse createSub(Subscription subscription) {
        subscriptionRepo.save(subscription);

        return new SuccessResponse("Абонемент успешно добавлен!", HttpStatus.OK.value());
    }

    @Transactional
    public SuccessResponse deleteSub(Integer id) {
        Optional<Subscription> subscription = subscriptionRepo.findById(id);
        if (subscription.isPresent()) {
            subscriptionRepo.deleteById(id);

            return new SuccessResponse("Абонемент успешно удален!", HttpStatus.OK.value());

        } else throw new ResourceNotFoundException("Абонемента с таким id: " + id + " не найдено!");
    }
}
