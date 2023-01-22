package com.example.crossFit.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "manager_id_seq", sequenceName = "MANAGER_ID_SEQ", allocationSize = 1)
    private Integer id;
    private String name;

    public Manager() {
    }

    public Manager(String name) {
        this.name = name;
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
}
