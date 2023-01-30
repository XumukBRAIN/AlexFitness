package com.example.crossFit.service;

import com.example.crossFit.model.entity.Accountant;
import com.example.crossFit.repository.AccountantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountantService {
    private final AccountantRepo accountantRepo;

    @Autowired
    public AccountantService(AccountantRepo accountantRepo) {
        this.accountantRepo = accountantRepo;
    }

    @Transactional(readOnly = true)
    public Accountant findByName(String name) {
        return accountantRepo.findByName(name);
    }

    @Transactional
    public void save(Accountant accountant) {
        accountantRepo.save(accountant);
    }

}
