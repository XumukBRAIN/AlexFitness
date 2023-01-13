package com.example.AlexFitness.repository;

import com.example.AlexFitness.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepo extends JpaRepository<Manager, Integer> {


}
