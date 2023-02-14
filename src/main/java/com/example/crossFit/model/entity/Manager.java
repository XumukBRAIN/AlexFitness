package com.example.crossFit.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "manager_id_seq", sequenceName = "MANAGER_ID_SEQ", allocationSize = 1)
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String role;
    @Column(name = "double_check")
    @JsonIgnore
    private boolean isDoubleCheckAuth;

    public Manager() {
    }

    public Manager(String name, String password, String email, String phoneNumber, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
