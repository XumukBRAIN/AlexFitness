package com.example.AlexFitness.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "coach")
public class Coach {
    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "coach")
    private List<Client> clients;

    @OneToMany(mappedBy = "coachId")
    private List<RequestFit> requestFits;

    public Coach() {
    }

    public Coach(int id, String name, List<Client> clients, List<RequestFit> requestFits) {
        this.id = id;
        this.name = name;
        this.clients = clients;
        this.requestFits = requestFits;
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
