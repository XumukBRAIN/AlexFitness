package com.example.AlexFitness.controller;

import com.example.AlexFitness.model.dto.CoachDTO;
import com.example.AlexFitness.model.entity.Coach;
import com.example.AlexFitness.model.mapStruct.CoachMapper;
import com.example.AlexFitness.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/coach")
public class CoachController {
    private final CoachService coachService;
    private final CoachMapper coachMapper;

    @Autowired
    public CoachController(CoachService coachService, CoachMapper coachMapper) {
        this.coachService = coachService;
        this.coachMapper = coachMapper;
    }

    @GetMapping("/getOne/{id}")
    public Optional<Coach> getCoach(@PathVariable Integer id) {
        return coachService.getCoach(id);
    }


    @GetMapping("/getCoach")
    public CoachDTO getCoachByName(@RequestParam String name) {
        Coach coach = coachService.findByName(name);
        return coachMapper.toCoachDTO(coach);
    }

    @PostMapping("/createCoach")
    public void createCoach(@RequestBody CoachDTO coachDTO) {
        Coach coach = coachMapper.toCoach(coachDTO);
        coachService.createCoach(coach);
    }

}
