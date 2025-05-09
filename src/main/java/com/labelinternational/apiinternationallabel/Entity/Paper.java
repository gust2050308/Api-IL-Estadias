package com.labelinternational.apiinternationallabel.Entity;

import jakarta.persistence.*;

@Entity
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_Paper;

    @Column(unique=true)
    private String material;

    @Column(unique=true)
    private Float width;

    @Column(unique=true)
    private Float large;

    public void setId_Paper(Long idPaper) {
        this.id_Paper = idPaper;
    }

    public Long getId_Paper() {
        return id_Paper;
    }
}
