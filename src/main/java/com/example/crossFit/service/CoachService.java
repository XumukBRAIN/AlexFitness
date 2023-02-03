package com.example.crossFit.service;

import com.example.crossFit.exceptions.EntityAlreadyIsRegisteredException;
import com.example.crossFit.exceptions.EntityNotFoundException;
import com.example.crossFit.model.entity.Coach;
import com.example.crossFit.repository.CoachRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CoachService {
    private final CoachRepo coachRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CoachService(CoachRepo coachRepo, PasswordEncoder passwordEncoder) {
        this.coachRepo = coachRepo;
        this.passwordEncoder = passwordEncoder;
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
        Coach coach1 = coachRepo.findByEmail(coach.getEmail());
        if (coach1 != null) {
            throw new EntityAlreadyIsRegisteredException(HttpStatus.BAD_REQUEST,
                    "Тренер с такой электронной почтой уже зарегистрирован");
        } else {
            passwordEncoder.encode(coach.getPassword());
            coachRepo.save(coach);
        }

    }

    @Transactional
    public void deleteCoach(Integer id) {
        Optional<Coach> coach = coachRepo.findById(id);
        if (!coach.isPresent()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Тренер с таким ID не найден в базе");
        }
        coachRepo.deleteById(id);
    }

}
