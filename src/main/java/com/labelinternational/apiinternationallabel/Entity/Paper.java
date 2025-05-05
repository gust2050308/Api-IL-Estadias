package com.labelinternational.apiinternationallabel.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Paper {

    @Id
    private Long id_Paper;

    public void setId_Paper(Long idPaper) {
        this.id_Paper = idPaper;
    }

    public Long getId_Paper() {
        return id_Paper;
    }
}
