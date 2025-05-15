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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;

    @OneToMany(mappedBy = "customer")
    private List<Production> productions;

    public void setId(Long id) {
        this.idCustomer = id;
    }

    public Long getId() {
        return idCustomer;
    }
}
