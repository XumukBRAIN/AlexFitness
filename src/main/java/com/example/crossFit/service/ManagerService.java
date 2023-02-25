package com.example.crossFit.service;

import com.example.crossFit.exceptions.ClientAlreadyIsBlockedException;
import com.example.crossFit.exceptions.ResourceAlreadyIsRegisteredException;
import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Client;
import com.example.crossFit.model.entity.Manager;
import com.example.crossFit.repository.ClientRepo;
import com.example.crossFit.repository.ManagerRepo;
import com.example.crossFit.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class ManagerService {
    private final ManagerRepo managerRepo;
    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;

    private static final Map<String, Integer> BAN_TERM = new HashMap<String, Integer>() {
        {
            put("1", 923847329);
            put("2", 7);
            put("3", 1);
        }
    };

    @Autowired
    public ManagerService(ManagerRepo managerRepo, ClientRepo clientRepo, PasswordEncoder passwordEncoder) {
        this.managerRepo = managerRepo;
        this.clientRepo = clientRepo;
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
        if (managerRepo.findByEmail(manager.getEmail()).isPresent()) {
            throw new ResourceAlreadyIsRegisteredException("Администратор с такой электронной почтой: "
                    + manager.getEmail() + " уже зарегистрирован!");
        }
        if (managerRepo.findByPhoneNumber(manager.getPhoneNumber()).isPresent()) {
            throw new ResourceAlreadyIsRegisteredException("Администратор с таким телефоном : "
                    + manager.getPhoneNumber() + " уже зарегистрирован!");
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public SuccessResponse banUser(UUID id, String typeTimeBan) {
        Optional<Client> clientOptional = clientRepo.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            if (!client.getAccountIsLocked()) {
                client.setAccountIsLocked(true);
                client.setEndBanDate(LocalDate.now().plusDays(BAN_TERM.get(typeTimeBan)));
                clientRepo.save(client);
            } else {
                if (client.getAccountIsLocked()) {
                    throw new ClientAlreadyIsBlockedException("Пользователь с таким id: " + id + " уже заблокирован!");
                } else {
                    throw new ResourceNotFoundException("Пользователь с таким id :" + id + " не зарегистрирован!");
                }
            }
        }
        return new SuccessResponse("Пользователь забанен!", HttpStatus.OK.value());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public SuccessResponse unbanUser(UUID id) {
        Optional<Client> clientOptional = clientRepo.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            if (client.getAccountIsLocked()) {
                client.setAccountIsLocked(false);
                client.setEndBanDate(null);
                clientRepo.save(client);
            }
        } else {
            throw new ResourceNotFoundException("Пользователь с таким id: " + id + " не найдент в списке забаненных");
        }
        return new SuccessResponse("Пользователь разблокирован!", HttpStatus.OK.value());
    }

    @Transactional
    @Scheduled(cron = "@daily")
    public void scheduleEndBanDate() {
        List<Client> clientOptionalList = clientRepo.findAllByAccountIsLockedTrue();
        for (Client client : clientOptionalList) {
            if (client != null) {
                if (client.getEndBanDate().equals(LocalDate.now())) {
                    client.setEndBanDate(null);
                    client.setAccountIsLocked(false);
                    clientRepo.save(client);
                }
            }
        }


    }
}
