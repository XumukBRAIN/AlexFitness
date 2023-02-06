package com.example.crossFit.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Accountant {

    @Id
    private Integer id;
    private String name;
    private BigDecimal balance;


    public Accountant(Integer id, String name, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

}
