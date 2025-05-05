package com.labelinternational.apiinternationallabel.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class InInk {
    @Id
    private Long id_InInk;

    public void setId_Ink(Long id_InInk) {
        this.id_InInk = id_InInk;
    }

    public Long getId_Ink() {
        return id_InInk;
    }
}
