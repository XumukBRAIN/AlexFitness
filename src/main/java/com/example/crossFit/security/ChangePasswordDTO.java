package com.example.crossFit.security;

public class ChangePasswordDTO {
    private String thisPassword;
    private String newPassword;

    public String getThisPassword() {
        return thisPassword;
    }

    public void setThisPassword(String thisPassword) {
        this.thisPassword = thisPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
