package com.example.AlexFitness.service;


import com.example.AlexFitness.entity.RequestFit;
import com.example.AlexFitness.repository.RequestFitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RequestFitService {
    public final RequestFitRepo requestFitRepo;

    @Autowired
    public RequestFitService(RequestFitRepo requestFitRepo) {
        this.requestFitRepo = requestFitRepo;
    }

    public RequestFit findByNubmerPhone(String phoneNumber) {
        return requestFitRepo.findByPhoneNumber(phoneNumber);
    }

    @Transactional
    public void createRequest(RequestFit requestFit) {
        requestFitRepo.save(requestFit);
    }
}
