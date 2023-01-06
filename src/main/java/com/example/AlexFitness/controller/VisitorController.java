package com.example.AlexFitness.controller;

import com.example.AlexFitness.entity.Visitor;
import com.example.AlexFitness.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/visitor")
public class VisitorController {
    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping("/getOne/{id}")
    public Optional<Visitor> getVisitor(@PathVariable Integer id) {
        return visitorService.getVisitor(id);
    }
}
