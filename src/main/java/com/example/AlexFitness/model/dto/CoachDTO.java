package com.example.AlexFitness.model.dto;

public class CoachDTO {

    private Integer coachId;
    private String coachName;


    public CoachDTO(Integer coachId, String coachName) {
        this.coachId = coachId;
        this.coachName = coachName;
    }


    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }
}
