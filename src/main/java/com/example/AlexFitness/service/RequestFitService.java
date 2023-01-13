package com.example.AlexFitness.service;


import com.example.AlexFitness.model.entity.RequestFit;
import com.example.AlexFitness.repository.RequestFitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestFitService {
    private final RequestFitRepo requestFitRepo;

    @Autowired
    public RequestFitService(RequestFitRepo requestFitRepo) {
        this.requestFitRepo = requestFitRepo;
    }

    public RequestFit findByNumberPhone(String phoneNumber) {
        return requestFitRepo.findByPhoneNumber(phoneNumber);
    }

    @Transactional
    public void createRequest(RequestFit requestFit) {
        requestFitRepo.save(requestFit);
    }

    public RequestFit findByPhoneNumber(String phoneNumber) {
        return requestFitRepo.findByPhoneNumber(phoneNumber);
    }

    @Transactional(readOnly = true)
    public List<RequestFit> findNotApprovedRequests() {
        return requestFitRepo.findAllByIsApprovedFalse();
    }

    @Transactional
    public void approve(RequestFit requestFit) {
        requestFitRepo.save(requestFit);
    }
}
