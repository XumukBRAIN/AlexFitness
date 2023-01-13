package com.example.AlexFitness.entity;

import javax.persistence.*;

@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "manager_id_seq", sequenceName = "MANAGER_ID_SEQ", allocationSize = 1)
    private int id;
    private String name;

    public Manager() {
    }

    public Manager(int id, String name) {
        this.id = id;
        this.name = name;
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
}
