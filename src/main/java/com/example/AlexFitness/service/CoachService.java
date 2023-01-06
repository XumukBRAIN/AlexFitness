package com.example.AlexFitness.service;

import com.example.AlexFitness.entity.Coach;
import com.example.AlexFitness.repository.CoachRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoachService {
    private final CoachRepo coachRepo;

    @Autowired
    public CoachService(CoachRepo coachRepo) {
        this.coachRepo = coachRepo;
    }

    public Optional<Coach> getCoach(Integer id) {
        return coachRepo.findById(id);
    }

    public Coach findByName(String name) {
        return coachRepo.findByName(name);
    }
}
