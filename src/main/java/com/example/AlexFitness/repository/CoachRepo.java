package com.example.AlexFitness.repository;

import com.example.AlexFitness.model.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CoachRepo extends JpaRepository<Coach, Integer> {

    Coach findByName(String name);

}
