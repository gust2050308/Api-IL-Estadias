package com.labelinternational.apiinternationallabel.Entity;

import jakarta.persistence.*;

@Entity
public class Ink {

    public Ink() {
    }

    public Ink(String material_ink, String code_ink, Float volume_ink) {
        this.material_ink = material_ink;
        this.code_ink = code_ink;
        this.volume_ink = volume_ink;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_ink;

    @Column(nullable = false)
    private String material_ink;

    @Column(nullable = false)
    private String code_ink;

    @Column(nullable = false)
    private Float volume_ink;

    public void setId_ink(Long idTinta) {
        this.id_ink = idTinta;
    }

    public Long getId_ink() {
        return id_ink;
    }

    public String getMaterial_ink() {
        return material_ink;
    }

    public Float getVolume_ink() {
        return volume_ink;
    }

    public String getCode_ink() {
        return code_ink;
    }

    public void setMaterial_ink(String material_ink) {
        this.material_ink = material_ink;
    }

    public void setCode_ink(String code_ink) {
        this.code_ink = code_ink;
    }

    public void setVolume_ink(Float volume_ink) {
        this.volume_ink = volume_ink;
    }
}
