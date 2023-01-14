package com.example.AlexFitness.repository;

import com.example.AlexFitness.model.entity.RequestFit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestFitRepo extends JpaRepository<RequestFit, Integer> {

    List<RequestFit> findAllByIsApprovedNull();

    RequestFit findByPhoneNumber(String phoneNumber);

}
