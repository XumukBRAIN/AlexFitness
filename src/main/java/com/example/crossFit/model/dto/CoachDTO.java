package com.example.crossFit.model.dto;

public class CoachDTO {

    private Integer coachId;
    private String coachName;
    private String coachPassword;
    private String coachEmail;
    private String coachPhoneNumber;

    public CoachDTO(Integer coachId, String coachName, String coachPassword, String coachEmail, String coachPhoneNumber) {
        this.coachId = coachId;
        this.coachName = coachName;
        this.coachPassword = coachPassword;
        this.coachEmail = coachEmail;
        this.coachPhoneNumber = coachPhoneNumber;
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

    public String getCoachPassword() {
        return coachPassword;
    }

    public void setCoachPassword(String coachPassword) {
        this.coachPassword = coachPassword;
    }

    public String getCoachEmail() {
        return coachEmail;
    }

    public void setCoachEmail(String coachEmail) {
        this.coachEmail = coachEmail;
    }

    public String getCoachPhoneNumber() {
        return coachPhoneNumber;
    }

    public void setCoachPhoneNumber(String coachPhoneNumber) {
        this.coachPhoneNumber = coachPhoneNumber;
    }
}
