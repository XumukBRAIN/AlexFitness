package com.example.AlexFitness.service;

import com.example.AlexFitness.model.entity.Coach;
import com.example.AlexFitness.repository.CoachRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CoachService {
    private final CoachRepo coachRepo;

    @Autowired
    public CoachService(CoachRepo coachRepo) {
        this.coachRepo = coachRepo;
    }

    @Transactional(readOnly = true)
    public Optional<Coach> getCoach(Integer id) {
        return coachRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public Coach findByName(String name) {
        return coachRepo.findByName(name);
    }

    @Transactional
    public void createCoach(Coach coach) {
        coachRepo.save(coach);
    }

}
