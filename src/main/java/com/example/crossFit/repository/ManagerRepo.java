package com.example.crossFit.repository;

import com.example.crossFit.model.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepo extends JpaRepository<Manager, Integer> {

    List<Manager> findByName(String name);

    Optional<Manager> findByEmail(String email);

    Optional<Manager> findByPhoneNumber(String phoneNumber);

}
