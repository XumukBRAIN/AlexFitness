package com.example.crossFit.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDTO {

    private Integer managerId;
    private String managerName;
    private String managerEmail;
    private String managerPassword;

    public ManagerDTO(Integer managerId, String managerName, String managerEmail, String managerPassword) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.managerEmail = managerEmail;
        this.managerPassword = managerPassword;
    }

}
