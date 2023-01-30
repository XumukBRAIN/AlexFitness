package com.example.crossFit.model.dto;

import java.math.BigDecimal;

public class ItemDTO {
    private Integer itemId;
    private String itemTitle;
    private BigDecimal itemPrice;

    public ItemDTO(Integer itemId, String itemTitle, BigDecimal itemPrice) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
}
