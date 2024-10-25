package com.example.StudyJava.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "manufacture")
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manufactureId;

    @Column(name = "manufacture_name", nullable = false)
    private String manufactureName;

    @OneToMany(mappedBy = "manufacture", cascade = CascadeType.ALL)
    private List<Product> products;
}
