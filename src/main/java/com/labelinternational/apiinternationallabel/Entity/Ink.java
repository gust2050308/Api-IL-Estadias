package com.labelinternational.apiinternationallabel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Ink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idInk;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "in_ink_id", nullable = false, unique = true)
    private InInk inInk;

    @Column(nullable = false)
    private Long totalKilograms;

    @Column(nullable = false)
    private Long volumeUsed = 0L;

    @Column(nullable = false)
    private Long remainingVolume ;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idProduction", nullable = true)
    private Production production;
}
