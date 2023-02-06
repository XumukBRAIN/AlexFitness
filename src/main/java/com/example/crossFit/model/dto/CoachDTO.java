package com.example.crossFit.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoachDTO {

    private Integer coachId;
    private String coachName;
    private String coachPassword;
    private String coachEmail;

    public CoachDTO(Integer coachId, String coachName, String coachPassword, String coachEmail) {
        this.coachId = coachId;
        this.coachName = coachName;
        this.coachPassword = coachPassword;
        this.coachEmail = coachEmail;
    }

}
