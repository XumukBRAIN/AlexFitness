package com.example.AlexFitness.repository;

import com.example.AlexFitness.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CoachRepo extends JpaRepository<Coach, Integer> {
}
