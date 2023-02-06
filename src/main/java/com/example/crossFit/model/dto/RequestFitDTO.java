package com.example.crossFit.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFitDTO {

    private Long requestFitId;
    private String requestFitTitle;
    private Integer requestFitSubId;
    private Integer requestFitCoachId;
    private String requestFitPhoneNumber;

    public RequestFitDTO(Long requestFitId, String requestFitTitle, Integer requestFitSubId, Integer requestFitCoachId, String requestFitPhoneNumber) {
        this.requestFitId = requestFitId;
        this.requestFitTitle = requestFitTitle;
        this.requestFitSubId = requestFitSubId;
        this.requestFitCoachId = requestFitCoachId;
        this.requestFitPhoneNumber = requestFitPhoneNumber;
    }


}
