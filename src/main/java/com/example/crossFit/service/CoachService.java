package com.example.crossFit.service;

import com.example.crossFit.exeptions.EntityNotFoundException;
import com.example.crossFit.model.entity.Coach;
import com.example.crossFit.repository.CoachRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Coach coach = coachRepo.findByName(name);
        if (coach == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Тренер с таким именем не найден в базе");
        }
        return coach;
    }

    @Transactional
    public void createCoach(Coach coach) {
        coachRepo.save(coach);
    }

}
