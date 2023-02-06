package com.example.crossFit.service;

import com.example.crossFit.exceptions.ResourceAlreadyIsRegistered;
import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Coach;
import com.example.crossFit.repository.CoachRepo;
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
    public Coach getCoach(Integer id) {
        Optional<Coach> coach = coachRepo.findById(id);
        if (!coach.isPresent()) {
            throw new ResourceNotFoundException("Тренер с id: " + id + " не найден в базе!");
        }
        return coach.get();
    }

    @Transactional(readOnly = true)
    public Coach findByName(String name) {
        Coach coach = coachRepo.findByName(name);
        if (coach == null) {
            throw new ResourceNotFoundException("Тренер с таким именем: " + name + " не зарегистрирован!");
        }
        return coach;
    }

    @Transactional
    public String createCoach(Coach coach) {
        if (coachRepo.findByEmail(coach.getEmail()) != null) {
            throw new ResourceAlreadyIsRegistered("Тренер с такой электронной почтой: " + coach.getEmail() + " " +
                    "уже зарегистрирован!");
        }
        coachRepo.save(coach);

        return "Тренер зарегистрирован!";
    }

    @Transactional
    public String deleteCoach(Integer id) {
        Optional<Coach> coach = coachRepo.findById(id);
        if (!coach.isPresent()) {
            throw new ResourceNotFoundException("Тренер с таким id: " + id + " уже зарегистрирован в базе!");
        }
        coachRepo.deleteById(id);
        return "Тренер удален!";
    }

}
