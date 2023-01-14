package com.example.AlexFitness.service;


import com.example.AlexFitness.model.entity.Manager;
import com.example.AlexFitness.repository.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ManagerService {
    private final ManagerRepo managerRepo;

    @Autowired
    public ManagerService(ManagerRepo managerRepo) {
        this.managerRepo = managerRepo;
    }

    @Transactional(readOnly = true)
    public Optional<Manager> getManager(Integer id) {
        return managerRepo.findById(id);
    }

    @Transactional
    public void createManager(Manager manager) {
        managerRepo.save(manager);
    }

    @Transactional(readOnly = true)
    public Manager findByName(String name) {
        return managerRepo.findByName(name);
    }
}
