package com.example.AlexFitness.controller;


import com.example.AlexFitness.entity.Manager;
import com.example.AlexFitness.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/getOne/{id}")
    public Optional<Manager> getManager(@PathVariable Integer id) {
        return managerService.getManager(id);
    }
}
