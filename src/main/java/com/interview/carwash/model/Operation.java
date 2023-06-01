package com.interview.carwash.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(schema = "carwash", name = "operation")
@Data
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String label;

    private int minutes;

}
