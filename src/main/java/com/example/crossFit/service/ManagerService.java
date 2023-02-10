package com.example.crossFit.service;

import com.example.crossFit.exceptions.ResourceAlreadyIsRegisteredException;
import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Manager;
import com.example.crossFit.repository.ManagerRepo;
import com.example.crossFit.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public Manager getManager(Integer id) {
        Optional<Manager> manager = managerRepo.findById(id);
        if (manager.isPresent()) {
            return manager.get();
        } else {
            throw new ResourceNotFoundException("Администратор с таким id: " + id + " не зарегистрирован!");
        }
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public SuccessResponse createManager(Manager manager) {
        Manager manager1 = managerRepo.findByEmail(manager.getEmail());
        if (manager1 != null) {
            throw new ResourceAlreadyIsRegisteredException("Администратор с такой электронной почтой: "
                    + manager.getEmail() + " уже зарегистрирован!");
        } else {
            manager.setPassword(passwordEncoder.encode(manager.getPassword()));
            manager.setRole("ROLE_ADMIN");
            managerRepo.save(manager);
        }

        return new SuccessResponse("Администратор успешно зарегистрирован!", HttpStatus.OK.value());
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    @Transactional(readOnly = true)
    public List<Manager> findByName(String name) {
        List<Manager> managers = managerRepo.findByName(name);
        if (managers.isEmpty()) {
            throw new ResourceNotFoundException("Администраторов с таким именем: " + name + " не найдено!");
        } else {
            return managers;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public SuccessResponse deleteManager(Integer id) {
        Optional<Manager> manager = managerRepo.findById(id);
        if (!manager.isPresent()) {
            throw new ResourceNotFoundException("Администратор с таким id: " + id + " не зарегистрирован!");
        }
        managerRepo.deleteById(id);

        return new SuccessResponse("Администратор успешно удален!", HttpStatus.OK.value());
    }

}
