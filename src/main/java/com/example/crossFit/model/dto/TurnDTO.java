package com.example.crossFit.model.dto;

import com.example.crossFit.model.entity.Turn.TurnSpecification;

import java.util.HashMap;
import java.util.Map;

public class TurnDTO {
    private Map<Integer, TurnSpecification> turnMap = new HashMap<>();

    public TurnDTO() {
    }

    public Map<Integer, TurnSpecification> getTurnMap() {
        return turnMap;
    }

    public void addToTurnMap(Integer id, TurnSpecification turnSpecification) {
        this.turnMap.put(id, turnSpecification);
    }
}
