package com.example.AlexFitness.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coach")
public class Coach {
    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "coach")
    private List<Visitor> visitors;

    public Coach() {
    }

    public Coach(int id, String name, List<Visitor> visitors) {
        this.id = id;
        this.name = name;
        this.visitors = visitors;
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

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }
}
