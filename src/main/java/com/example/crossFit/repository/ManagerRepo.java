package com.example.crossFit.repository;

import com.example.crossFit.model.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepo extends JpaRepository<Manager, Integer> {

    List<Manager> findByName(String name);

    Manager findByEmail(String email);

    Manager findByPhoneNumber(String phoneNumber);

}
