package com.example.crossFit.repository;

import com.example.crossFit.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

    Optional<Client> findByPhoneNumber(String phoneNumber);

    Optional<Client> findById(UUID id);

    Optional<Client> findByEmail(String email);

    List<Client> findAllByAccountIsLockedTrue();

}
