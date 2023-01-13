package com.example.AlexFitness.controller;


import com.example.AlexFitness.model.dto.ManagerDTO;
import com.example.AlexFitness.model.entity.Manager;
import com.example.AlexFitness.model.mapStruct.ManagerMapper;
import com.example.AlexFitness.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;
    private final ManagerMapper managerMapper;

    @Autowired
    public ManagerController(ManagerService managerService, ManagerMapper managerMapper) {
        this.managerService = managerService;
        this.managerMapper = managerMapper;
    }


    @GetMapping("/getOne")
    public Optional<Manager> getManagerById(@RequestParam Integer id) {
        return managerService.getManager(id);
    }

    @GetMapping("/getOneByName")
    public ManagerDTO getManagerByName(@RequestParam String name) {
        Manager manager = managerService.findByName(name);
        return managerMapper.toManagerDTO(manager);
    }

    @PostMapping("/createManager")
    public void createManager(@RequestBody ManagerDTO managerDTO) {
        Manager manager = managerMapper.toManager(managerDTO);
        managerService.createManager(manager);
    }
}
