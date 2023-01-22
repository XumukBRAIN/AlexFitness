package com.example.crossFit.repository;

import com.example.crossFit.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Integer> {

}
