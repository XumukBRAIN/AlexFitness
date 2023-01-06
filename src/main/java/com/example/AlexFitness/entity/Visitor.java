package com.example.AlexFitness.entity;

import javax.persistence.*;

@Entity
@Table(name = "visitor")
public class Visitor {

    @Id
    private int id;
    private String name;
    private String phoneNumber;

    @JoinColumn(name = "coach")
    private int coach;

    public Visitor() {
    }

    public Visitor(int id, String name, String phoneNumber, int coach) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.coach = coach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getCoach() {
        return coach;
    }

    public void setCoach(int coach) {
        this.coach = coach;
    }
}
