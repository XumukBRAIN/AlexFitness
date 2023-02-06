package com.example.crossFit.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "item_id_seq", sequenceName = "ITEM_ID_SEQ", allocationSize = 1)
    private Integer id;
    private String title;
    private BigDecimal price;
    @ManyToMany(mappedBy = "items")
    private List<Orders> orders;

    public Item(Integer id, String title, List<Orders> orders, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.orders = orders;
        this.price = price;
    }


}
