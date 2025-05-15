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

    @OneToMany(mappedBy = "production")
    private List<Ink> inks;

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
