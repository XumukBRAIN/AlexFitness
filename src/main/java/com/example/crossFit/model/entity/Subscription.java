package com.example.crossFit.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Integer id;
    private String title;

    @Min(value = 0, message = "Стоимость абонемента не может быть ниже нуля")
    private BigDecimal price;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "subscriptionId")
    private List<Client> clients;

    @JsonIgnore
    @OneToMany(mappedBy = "subId")
    private List<RequestFit> requestFits;

    public Subscription() {
    }

    public Subscription(Integer id, String title, BigDecimal price, String description, List<Client> clients, List<RequestFit> requestFits) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.clients = clients;
        this.requestFits = requestFits;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<RequestFit> getRequestFits() {
        return requestFits;
    }

    public void setRequestFits(List<RequestFit> requestFits) {
        this.requestFits = requestFits;
    }
}
