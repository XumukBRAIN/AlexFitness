package com.example.crossFit.model.dto;

import com.example.crossFit.model.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public class OrdersDTO {
    private Integer ordersId;
    private String ordersNumber;
    private String ordersTitle;
    private BigDecimal ordersSum;
    private List<Item> ordersItems;
    private String ordersPhoneNumber;

    public OrdersDTO(Integer ordersId, String ordersNumber, String ordersTitle, BigDecimal ordersSum,
                     List<Item> ordersItems, String ordersPhoneNumber) {
        this.ordersId = ordersId;
        this.ordersNumber = ordersNumber;
        this.ordersTitle = ordersTitle;
        this.ordersSum = ordersSum;
        this.ordersItems = ordersItems;
        this.ordersPhoneNumber = ordersPhoneNumber;
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

    public List<Item> getOrdersItems() {
        return ordersItems;
    }

    public void setOrdersItems(List<Item> ordersItems) {
        this.ordersItems = ordersItems;
    }

    public String getOrdersPhoneNumber() {
        return ordersPhoneNumber;
    }

    public void setOrdersPhoneNumber(String ordersPhoneNumber) {
        this.ordersPhoneNumber = ordersPhoneNumber;
    }
}
