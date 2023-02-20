package com.example.crossFit.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "item_id_seq", sequenceName = "ITEM_ID_SEQ", allocationSize = 1)
    private Integer id;
    private String title;
    private BigDecimal price;
    @ManyToMany(mappedBy = "items")
    private List<Orders> orders;

    public Item() {
    }

    public Item(Integer id, String title, List<Orders> orders, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
