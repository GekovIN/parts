package com.gekov.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "part")
public class Part implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean required;
    private int quantity;

    public Part() {}

    public Part(String name) {
        this(name, false, 0);
    }

    public Part(String name, boolean required) {
        this(name, required, 0);
    }

    public Part(String name, int quantity) {
        this(name, false, quantity);
    }

    public Part(String name, boolean required, int quantity) {
        this.name = name;
        this.required = required;
        this.quantity = quantity;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "REQUIRED")
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Column(name = "QUANTITY")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", required=" + required +
                ", quantity=" + quantity +
                '}';
    }
}
