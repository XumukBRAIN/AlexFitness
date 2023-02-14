package com.example.crossFit.service;

import com.example.crossFit.exceptions.ResourceAlreadyIsRegisteredException;
import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Coach;
import com.example.crossFit.repository.CoachRepo;
import com.example.crossFit.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    @Transactional(readOnly = true)
    public Coach getCoach(Integer id) {
        Optional<Coach> coach = coachRepo.findById(id);
        if (!coach.isPresent()) {
            throw new ResourceNotFoundException("Тренер с таким id: " + id + " не зарегистрирован!");
        }
        return coach.get();
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public SuccessResponse register(Coach coach) {
        if (coachRepo.findByEmail(coach.getEmail()) != null) {
            throw new ResourceAlreadyIsRegisteredException("Тренер с такой электронной почтой: "
                    + coach.getEmail() + " уже зарегистрирован!");
        }
        if (coachRepo.findByPhoneNumber(coach.getPhoneNumber()) != null) {
            throw new ResourceAlreadyIsRegisteredException("Тренер с таким телефоном : "
                    + coach.getPhoneNumber() + " уже зарегистрирован!");
        } else {
            coach.setPassword(passwordEncoder.encode(coach.getPassword()));
            coach.setRole("ROLE_COACH");
            coachRepo.save(coach);
        }

        return new SuccessResponse("Тренер успешно зарегистрирован!", HttpStatus.OK.value());

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public SuccessResponse deleteCoach(Integer id) {
        Optional<Coach> coach = coachRepo.findById(id);
        if (!coach.isPresent()) {
            throw new ResourceNotFoundException("Тренер с таким id: " + id + " не зарегистрирован!");
        }
        coachRepo.deleteById(id);

        return new SuccessResponse("Тренер успешно удален!", HttpStatus.OK.value());
    }

}
