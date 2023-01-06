package com.example.AlexFitness.repository;

import com.example.AlexFitness.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepo extends JpaRepository<Visitor, Integer> {

    Visitor findByPhoneNumber(String phone);

    boolean isRegister(Integer id);
}
