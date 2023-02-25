package com.example.crossFit.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    @Size(min = 11, max = 11, message = "Номер начинается с цифры 8. Ожидается 11 цифр")
    private String phoneNumber;
    @JoinColumn(name = "coach")
    private Integer coach;
    @JoinColumn(name = "subscription_id")
    private Integer subscriptionId;
    private String email;
    @JsonIgnore
    private String password;
    private BigDecimal balance;
    @JsonIgnore
    @OneToMany(mappedBy = "clientId")
    private List<Orders> orders;
    @JsonIgnore
    private String role;
    @Column(name = "double_check")
    @JsonIgnore
    private Boolean isDoubleCheckAuth;
    @JsonIgnore
    private Boolean accountIsLocked = false;
    @JsonIgnore
    private LocalDate endBanDate;


    public Client() {
    }

    public Client(String name, String phoneNumber, Integer coach, Integer subscriptionId,
                  List<Orders> orders, String email, BigDecimal balance, String password, String role,
                  Boolean isDoubleCheckAuth, Boolean accountIsLocked, LocalDate endBanDate) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.coach = coach;
        this.subscriptionId = subscriptionId;
        this.email = email;
        this.balance = balance;
        this.orders = orders;
        this.password = password;
        this.role = role;
        this.isDoubleCheckAuth = isDoubleCheckAuth;
        this.accountIsLocked = accountIsLocked;
        this.endBanDate = endBanDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getCoach() {
        return coach;
    }

    public void setCoach(Integer coach) {
        this.coach = coach;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDoubleCheckAuth() {
        return isDoubleCheckAuth;
    }

    public void setDoubleCheckAuth(boolean doubleCheckAuth) {
        isDoubleCheckAuth = doubleCheckAuth;
    }

    public boolean getAccountIsLocked() {
        return accountIsLocked;
    }

    public void setAccountIsLocked(Boolean accountIsLocked) {
        this.accountIsLocked = accountIsLocked;
    }

    public LocalDate getEndBanDate() {
        return endBanDate;
    }

    public void setEndBanDate(LocalDate endBanDate) {
        this.endBanDate = endBanDate;
    }
}
