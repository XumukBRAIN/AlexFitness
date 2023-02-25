package com.example.crossFit.response;

public class SuccessAuthentication {
    private String result;
    private String message;
    private Integer status;

    public SuccessAuthentication(String result, String message, Integer status) {
        this.result = result;
        this.message = message;
        this.status = status;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
