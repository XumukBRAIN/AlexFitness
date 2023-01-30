package com.example.crossFit.repository;

import com.example.crossFit.model.entity.Accountant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountantRepo extends JpaRepository<Accountant, Integer> {

    Accountant findByName(String name);

}
