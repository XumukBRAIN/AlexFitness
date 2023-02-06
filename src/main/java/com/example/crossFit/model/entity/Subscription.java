package com.example.crossFit.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
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


    public Subscription(Integer id, String title, BigDecimal price, String description, List<Client> clients, List<RequestFit> requestFits) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.clients = clients;
        this.requestFits = requestFits;
    }


}
