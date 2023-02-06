package com.example.crossFit.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SubscriptionDTO {

    private Integer subId;
    private String subTitle;
    private BigDecimal subPrice;
    private String subDescription;

    public SubscriptionDTO(Integer subId, String subTitle, BigDecimal subPrice, String subDescription) {
        this.subId = subId;
        this.subTitle = subTitle;
        this.subPrice = subPrice;
        this.subDescription = subDescription;
    }

}
