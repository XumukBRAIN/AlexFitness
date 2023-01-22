package com.example.crossFit.repository;

import com.example.crossFit.model.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepo extends JpaRepository<Manager, Integer> {

    Manager findByName(String name);


}
