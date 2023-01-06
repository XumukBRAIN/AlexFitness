package com.example.AlexFitness.controller;

import com.example.AlexFitness.entity.Coach;
import com.example.AlexFitness.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/coach")
public class CoachController {
    private final CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/getOne/{id}")
    public Optional<Coach> getCoach(@PathVariable Integer id) {
        return coachService.getCoach(id);
    }

    @GetMapping("/getCoach/{name}")
    public Coach findByName(@PathVariable String name) {
        return coachService.findByName(name);
    }
}
