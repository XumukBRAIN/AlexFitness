package com.example.crossFit.response;

import java.util.HashMap;

public class SuccessAuthentication {
    private String message;
    private Integer status;
    private HashMap<String, String> map;

    public SuccessAuthentication(HashMap<String, String> map, String message, Integer status) {
        this.map = map;
        this.message = message;
        this.status = status;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
