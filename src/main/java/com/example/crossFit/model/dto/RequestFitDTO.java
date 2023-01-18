package com.example.crossFit.model.dto;

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

    public Long getRequestFitId() {
        return requestFitId;
    }

    public void setRequestFitId(Long requestFitId) {
        this.requestFitId = requestFitId;
    }

    public String getRequestFitTitle() {
        return requestFitTitle;
    }

    public void setRequestFitTitle(String requestFitTitle) {
        this.requestFitTitle = requestFitTitle;
    }

    public Integer getRequestFitSubId() {
        return requestFitSubId;
    }

    public void setRequestFitSubId(Integer requestFitSubId) {
        this.requestFitSubId = requestFitSubId;
    }

    public Integer getRequestFitCoachId() {
        return requestFitCoachId;
    }

    public void setRequestFitCoachId(Integer requestFitCoachId) {
        this.requestFitCoachId = requestFitCoachId;
    }

    public String getRequestFitPhoneNumber() {
        return requestFitPhoneNumber;
    }

    public void setRequestFitPhoneNumber(String requestFitPhoneNumber) {
        this.requestFitPhoneNumber = requestFitPhoneNumber;
    }
}
