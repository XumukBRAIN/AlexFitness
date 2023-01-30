package com.example.crossFit.model.dto;

import java.math.BigDecimal;

public class OrdersDTO {
    private Integer ordersId;
    private String ordersNumber;
    private String ordersTitle;
    private BigDecimal ordersSum;

    public OrdersDTO(Integer ordersId, String ordersNumber, String ordersTitle, BigDecimal ordersSum) {
        this.ordersId = ordersId;
        this.ordersNumber = ordersNumber;
        this.ordersTitle = ordersTitle;
        this.ordersSum = ordersSum;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public String getOrdersNumber() {
        return ordersNumber;
    }

    public void setOrdersNumber(String ordersNumber) {
        this.ordersNumber = ordersNumber;
    }

    public String getOrdersTitle() {
        return ordersTitle;
    }

    public void setOrdersTitle(String ordersTitle) {
        this.ordersTitle = ordersTitle;
    }

    public BigDecimal getOrdersSum() {
        return ordersSum;
    }

    public void setOrdersSum(BigDecimal ordersSum) {
        this.ordersSum = ordersSum;
    }

}
