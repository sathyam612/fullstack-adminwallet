package com.adminwallet.Entity;


import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Currency implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String name;
    private Double value;
    private String contract;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Currency(Long id, String name, Double value, String contract) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", contract='" + contract + '\'' +
                '}';
    }

    public Currency() {
    }
}
