package com.example.crossFit.service;

import com.example.crossFit.exceptions.EntityAlreadyIsRegisteredException;
import com.example.crossFit.exceptions.EntityNotFoundException;
import com.example.crossFit.model.entity.Manager;
import com.example.crossFit.repository.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ManagerService {
    private final ManagerRepo managerRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ManagerService(ManagerRepo managerRepo, PasswordEncoder passwordEncoder) {
        this.managerRepo = managerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    public Optional<Manager> getManager(Integer id) {
        return managerRepo.findById(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void createManager(Manager manager) {
        Manager manager1 = managerRepo.findByEmail(manager.getEmail());
        if (manager1 != null) {
            throw new EntityAlreadyIsRegisteredException(HttpStatus.BAD_REQUEST,
                    "Администратор с такой почтой уже зарегистрирован");
        } else {
            manager.setPassword(passwordEncoder.encode(manager.getPassword()));
            manager.setRole("ROLE_ADMIN");
            managerRepo.save(manager);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    @Transactional(readOnly = true)
    public Manager findByName(String name) {
        return managerRepo.findByName(name);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
