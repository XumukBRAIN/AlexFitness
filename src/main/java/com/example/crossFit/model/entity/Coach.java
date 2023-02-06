package com.example.crossFit.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coach")
@Getter
@Setter
@NoArgsConstructor
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "coach_id_seq", sequenceName = "COACH_ID_SEQ", allocationSize = 1)
    private Integer id;

    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "coach")
    private List<Client> clients;

    @JsonIgnore
    @OneToMany(mappedBy = "coachId")
    private List<RequestFit> requestFits;


    public Coach(String name, List<Client> clients, List<RequestFit> requestFits,
                 String password, String email, String role) {
        this.name = name;
        this.clients = clients;
        this.requestFits = requestFits;
        this.password = password;
        this.email = email;
        this.role = role;
    }


}
