package com.example.crossFit.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "orders_id_seq", sequenceName = "ORDERS_ID_SEQ", allocationSize = 1)
    private Integer id;
    private String number;
    private String title;
    private BigDecimal sum;
    private String phoneNumber;
    @CreationTimestamp
    private LocalDateTime reqDate;
    @JoinColumn(name = "client_id")
    private UUID clientId;
    @ManyToMany
    @JoinTable(name = "orders_item",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

    public Orders() {
    }

    public Orders(Integer id, String number, String title, BigDecimal sum, UUID clientId,
                  List<Item> items, LocalDateTime reqDate, String phoneNumber) {
        this.id = id;
        this.number = number;
        this.title = title;
        this.sum = sum;
        this.clientId = clientId;
        this.items = items;
        this.reqDate = reqDate;
        this.phoneNumber = phoneNumber;
    }


    public LocalDateTime getReqDate() {
        return reqDate;
    }

    public void setReqDate(LocalDateTime reqDate) {
        this.reqDate = reqDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
