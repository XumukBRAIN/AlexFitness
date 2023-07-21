package com.example.crossFit.controller;

import com.example.crossFit.config.SwaggerConfig;
import com.example.crossFit.model.dto.TurnDTO;
import com.example.crossFit.service.TurnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Api(tags = SwaggerConfig.TURN_TAG)
@RestController
@RequestMapping("/turn")
public class TurnController {

    private final TurnService turnService;

    @Autowired
    public TurnController(TurnService turnService) {
        this.turnService = turnService;
    }

    @ApiOperation("Метод для получения всех услуг")
    @GetMapping("/getAll")
    public ResponseEntity<TurnDTO> getAllTurns() {
        return ResponseEntity.ok(turnService.getAll());
    }

    @ApiOperation("Метод для получения услуг по id или списку id")
    @GetMapping("/getById")
    public ResponseEntity<TurnDTO> getTurnsByListId(@RequestParam Collection<Integer> id) {
        return ResponseEntity.ok(turnService.getTurnsById(id));
    }

}


