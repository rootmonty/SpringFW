package com.test.learningdi.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Employee {

    private @Id @GeneratedValue Long id;

    @NotNull
    private String name;

    @NotNull
    private String role;

    public Employee() {}

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

}
