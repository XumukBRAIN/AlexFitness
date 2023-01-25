package com.example.crossFit.model.dto;

public class ManagerDTO {

    private Integer managerId;
    private String managerName;

    public ManagerDTO(Integer managerId, String managerName) {
        this.managerId = managerId;
        this.managerName = managerName;
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

}
