package com.example.crossFit.controller;

import com.example.crossFit.model.dto.CoachDTO;
import com.example.crossFit.model.entity.Coach;
import com.example.crossFit.model.mapStruct.CoachMapper;
import com.example.crossFit.service.CoachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api("Контроллер для тренера")
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

    @ApiOperation("Метод для поиска тренера по ID")
    @GetMapping("/getOne/{id}")
    public Optional<Coach> getCoach(@PathVariable Integer id) {
        return coachService.getCoach(id);
    }

    @ApiOperation("Метод для поиска тренера по имени")
    @GetMapping("/getCoach")
    public CoachDTO getCoachByName(@RequestParam String name) {
        Coach coach = coachService.findByName(name);
        return coachMapper.toCoachDTO(coach);
    }

    @ApiOperation("Метод для добавления тренера в базу")
    @PostMapping("/createCoach")
    public void createCoach(@RequestBody CoachDTO coachDTO) {
        Coach coach = coachMapper.toCoach(coachDTO);
        coachService.createCoach(coach);
    }

}
