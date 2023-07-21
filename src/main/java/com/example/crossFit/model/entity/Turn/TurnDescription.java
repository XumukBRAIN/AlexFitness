package com.example.crossFit.model.entity.Turn;

public class TurnDescription {

    private final Integer id;
    private final String description;
    private final String serviceTime;

    public TurnDescription(Integer id, String description, String serviceTime) {
        this.id = id;
        this.description = description;
        this.serviceTime = serviceTime;
    }

    public String getDescription() {
        return description;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public Integer getId() {
        return id;
    }
}
