package com.labelinternational.apiinternationallabel.Entity;

import jakarta.persistence.*;
import lombok.*;

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

    public void setId(Long id) {
        this.idProduction = id;
    }

    public Long getId() {
        return idProduction;
    }
}
