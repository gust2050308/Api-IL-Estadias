package com.labelinternational.apiinternationallabel.Entity;

import com.labelinternational.apiinternationallabel.Entity.Enums.DesignState;
import jakarta.persistence.*;

@Entity
public class DesignProduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDesignProduction;

    @Column
    private DesignState designState;

    @Column
    private Boolean CustomerAuth = false;

    @Column
    private Boolean emissionVerification = false;

    @Column
    private Boolean designVerification = false;
}
