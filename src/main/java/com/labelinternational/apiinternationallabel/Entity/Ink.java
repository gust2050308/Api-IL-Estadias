package com.labelinternational.apiinternationallabel.Entity;

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

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private InInk inInk;

    @Column(nullable = false)
    private Long volumeUsed;

    @Column(nullable = false)
    private Long remainingVolume;

    @ManyToOne
    @JoinColumn(name = "idProduction")
    private Production production;
}
