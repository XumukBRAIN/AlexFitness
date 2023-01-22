package com.example.crossFit.service;

import com.example.crossFit.exceptions.EntityNotFoundException;
import com.example.crossFit.model.entity.Manager;
import com.example.crossFit.repository.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Transactional
    public void deleteManager(Integer id) {
        Optional<Manager> manager = managerRepo.findById(id);
        if (!manager.isPresent()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Менеджер с таким ID не найден в базе");
        }
        managerRepo.deleteById(id);
    }
}
