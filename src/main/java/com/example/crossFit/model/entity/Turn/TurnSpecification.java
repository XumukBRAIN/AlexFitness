package com.example.crossFit.model.entity.Turn;

public class TurnSpecification {
    private String description;
    private String serviceTime;

    public TurnSpecification(String description, String serviceTime) {
        this.description = description;
        this.serviceTime = serviceTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }
}
