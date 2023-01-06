package com.example.AlexFitness.service;

import com.example.AlexFitness.entity.Visitor;
import com.example.AlexFitness.repository.VisitorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitorService {
    private final VisitorRepo visitorRepo;

    @Autowired
    public VisitorService(VisitorRepo visitorRepo) {
        this.visitorRepo = visitorRepo;
    }

    public Optional<Visitor> getVisitor(Integer id) {
        return visitorRepo.findById(id);
    }

    public Visitor findByPhoneNumber(String phone) {
        return visitorRepo.findByPhoneNumber(phone);
    }

    public Visitor registerVisitor(Visitor visitor) {
        if (visitorRepo.isRegister(visitor.getId())) {
            return visitor;
        } else {
            return visitorRepo.save(visitor);
        }
    }


}
