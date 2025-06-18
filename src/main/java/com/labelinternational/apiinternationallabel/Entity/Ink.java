package com.labelinternational.apiinternationallabel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Ink {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ink_seq")
    @SequenceGenerator(
            name = "ink_seq",
            sequenceName = "ink_sequence",
            initialValue = 1000,
            allocationSize = 3)
    @Column(unique = true, nullable = false)
    private Long idInk;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "in_ink_id", nullable = false, unique = true)
    private InInk inInk;

    @Column(nullable = false)
    private BigDecimal totalKilograms;

    @Column(nullable = false)
    private BigDecimal volumeUsed = BigDecimal.valueOf(0);

    @Column(nullable = false)
    private BigDecimal remainingVolume ;
/*
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOutputInk")
    private List<OutputInk> inks;*/
}
