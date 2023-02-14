package com.example.crossFit.model.dto;

public class ManagerDTO {

    private Integer managerId;
    private String managerName;
    private String managerEmail;
    private String managerPhoneNumber;
    private String managerPassword;

    public ManagerDTO(Integer managerId, String managerName, String managerEmail, String managerPhoneNumber, String managerPassword) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.managerEmail = managerEmail;
        this.managerPhoneNumber = managerPhoneNumber;
        this.managerPassword = managerPassword;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public void setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }
}
