package com.example.crossFit.service;

import com.example.crossFit.exceptions.ResourceAlreadyIsRegistered;
import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Manager;
import com.example.crossFit.repository.ManagerRepo;
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
    public Manager getManager(Integer id) {
        Optional<Manager> manager = managerRepo.findById(id);
        if (!manager.isPresent()) {
            throw new ResourceNotFoundException("Администратор с таким id: " + id + " не зарегистрирован в базе!");
        }
        return manager.get();
    }

    @Transactional
    public String createManager(Manager manager) {
        if (managerRepo.findByEmail(manager.getEmail()) != null) {
            throw new ResourceAlreadyIsRegistered("Администратор с такой электронной почтой: " + manager.getEmail() +
                    " не зарегистрирован в базе!");
        }
        managerRepo.save(manager);
        return "Администратор зарегистрирован!";
    }

    @Transactional(readOnly = true)
    public Manager findByName(String name) {
        return managerRepo.findByName(name);
    }

    @Transactional
    public String deleteManager(Integer id) {
        Optional<Manager> manager = managerRepo.findById(id);
        if (!manager.isPresent()) {
            throw new ResourceNotFoundException("Администратор с таким id: " + id + "не зарегистрирован!");
        }
        managerRepo.deleteById(id);
        return "Администратор удален!";
    }

}
