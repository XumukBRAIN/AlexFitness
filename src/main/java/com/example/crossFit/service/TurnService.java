package com.example.crossFit.service;

import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.dto.TurnDTO;
import com.example.crossFit.model.entity.Turn.Turn;
import com.example.crossFit.model.entity.Turn.TurnDescription;
import com.example.crossFit.model.entity.Turn.TurnSpecification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис для работы с услугами зала
 */
@Service
public class TurnService {

    private Map<Integer, TurnDescription> cashTurns = null;

    @PreAuthorize("hasRole('ROLE_USER')")
    public TurnDTO getTurnsById(Collection<Integer> listId) {
        Map<Integer, TurnDescription> turnDescriptionMap = new HashMap<>();
        for (Integer id : listId) {
            for (Map.Entry<Integer, TurnDescription> map : getTurn(id).entrySet()) {
                if (map.getKey().equals(id)) {
                    turnDescriptionMap.put(map.getKey(), cashTurns.get(map.getKey()));
                }
            }
        }
        return toMapTurnDTO(turnDescriptionMap);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public TurnDTO getAll() {
        return toMapTurnDTO(getAllTurns());
    }


    private Map<Integer, TurnDescription> getTurn(Integer id) {
        if (cashTurns == null) {
            fillData();
        }
        return cashTurns.entrySet().stream()
                .filter(entry -> entry.getKey().equals(id))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    private Map<Integer, TurnDescription> getAllTurns() {
        if (cashTurns == null) {
            fillData();
        }
        return cashTurns;
    }

    private void fillData() {
        cashTurns = Arrays.stream(Turn.values())
                .filter(turn -> turn.getDescription() != null)
                .filter(turn -> turn.getServiceTime() != null)
                .filter(turn -> !Turn.UNKNOWN.equals(turn))
                .collect(Collectors.toMap(Turn::getId,
                        v -> new TurnDescription(v.getId(), v.getDescription(), v.getServiceTime())
                ));
    }

    private TurnDTO toMapTurnDTO(Map<Integer, TurnDescription> turnDescriptionMap) {
        if (turnDescriptionMap.isEmpty())
            throw new ResourceNotFoundException("Услуга не найдена!");
        TurnDTO turnDTO = new TurnDTO();
        for (Map.Entry<Integer, TurnDescription> map : turnDescriptionMap.entrySet()) {
            turnDTO.addToTurnMap(map.getKey(),
                    new TurnSpecification(map.getValue().getDescription(), map.getValue().getServiceTime()));
        }
        return turnDTO;
    }
}
