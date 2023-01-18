package com.example.crossFit.model.dto;

import java.math.BigDecimal;

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

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public BigDecimal getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(BigDecimal subPrice) {
        this.subPrice = subPrice;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public void setSubDescription(String subDescription) {
        this.subDescription = subDescription;
    }
}
