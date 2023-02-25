package com.example.crossFit.repository;

import com.example.crossFit.model.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepo extends JpaRepository<Coach, Integer> {

    Optional<Coach> findByName(String name);

    Optional<Coach> findByEmail(String email);

    Optional<Coach> findByPhoneNumber(String phoneNumber);

}
