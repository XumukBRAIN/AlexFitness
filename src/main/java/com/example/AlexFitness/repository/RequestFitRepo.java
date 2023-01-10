package com.example.AlexFitness.repository;

import com.example.AlexFitness.entity.RequestFit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestFitRepo extends JpaRepository<RequestFit, Integer> {

    RequestFit findById(UUID id);

    RequestFit findByPhoneNumber(String phoneNumber);
}
