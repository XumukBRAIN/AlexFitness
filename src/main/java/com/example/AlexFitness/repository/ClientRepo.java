package com.example.AlexFitness.repository;

import com.example.AlexFitness.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

    Client findByPhoneNumber(String phoneNumber);

    Optional<Client> findById(UUID id);
}
