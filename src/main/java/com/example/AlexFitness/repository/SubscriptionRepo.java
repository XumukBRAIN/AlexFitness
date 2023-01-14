package com.example.AlexFitness.repository;

import com.example.AlexFitness.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Integer> {


    //List<Subscription> findAll();
}
