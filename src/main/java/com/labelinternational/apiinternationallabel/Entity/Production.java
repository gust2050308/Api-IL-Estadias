package com.labelinternational.apiinternationallabel.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduction;

    @Column(nullable = false, unique = true)
    private Long noProductionOrder;

    @OneToMany(mappedBy = "production")
    private List<OutputInk> outputInks;

    @ManyToOne
    @JoinColumn(name = "idCustomer")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "idDesignProduction")
    private DesignProduction designProduction;

    @OneToOne
    @JoinColumn(name = "idFinishes")
    private Finishes finishes;

    @OneToOne
    @JoinColumn(name = "idPrePress")
    private PrePress prePress;

    @OneToOne
    @JoinColumn(name = "idPress")
    private Press Press;
}
