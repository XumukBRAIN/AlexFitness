package com.example.AlexFitness.model.dto;

public class ManagerDTO {

    private Integer ManagerId;
    private String ManagerName;


    public ManagerDTO(Integer managerId, String managerName) {
        ManagerId = managerId;
        ManagerName = managerName;
    }

    public Integer getManagerId() {
        return ManagerId;
    }

    public void setManagerId(Integer managerId) {
        ManagerId = managerId;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public void setManagerName(String managerName) {
        ManagerName = managerName;
    }
}
