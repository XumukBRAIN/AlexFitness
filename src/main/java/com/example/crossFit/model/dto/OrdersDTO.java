package com.example.crossFit.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
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

}
