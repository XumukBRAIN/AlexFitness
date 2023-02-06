package com.example.crossFit.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemDTO {
    private Integer itemId;
    private String itemTitle;
    private BigDecimal itemPrice;

    public ItemDTO(Integer itemId, String itemTitle, BigDecimal itemPrice) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
    }

}
