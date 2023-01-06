package com.example.AlexFitness.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
public class Subscription {

    @Id
    private int id;
    private String title;

    @Min(value = 0, message = "Стоимость абонемента не может быть ниже нуля")
    private BigDecimal price;
    private String description;

    @OneToMany(mappedBy = "subscription_id")
    private List<Visitor> visitors;

    public Subscription() {
    }

    public Subscription(int id, String title, BigDecimal price, String description, List<Visitor> visitors) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.visitors = visitors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }
}
