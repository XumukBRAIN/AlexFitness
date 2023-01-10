package com.example.AlexFitness.service;


import com.example.AlexFitness.entity.Manager;
import com.example.AlexFitness.repository.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService {
    private final ManagerRepo managerRepo;

    @Autowired
    public ManagerService(ManagerRepo managerRepo) {
        this.managerRepo = managerRepo;
    }

    public Optional<Manager> getManager(Integer id) {
        return managerRepo.findById(id);
    }
}
