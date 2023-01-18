package com.example.crossFit.repository;

import com.example.crossFit.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

    Client findByPhoneNumber(String phoneNumber);

    Client findById(UUID id);

    Client findByEmail(String email);

}
