package com.labelinternational.apiinternationallabel.Entity;

import com.labelinternational.apiinternationallabel.Entity.Enums.ProviderType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Query;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "provider_seq")
    @SequenceGenerator(
            name = "provider_seq",
            sequenceName = "provider_sequence",
            initialValue = 1000,
            allocationSize = 2)
    @Column(unique = true, nullable = false)
    private Long idProvider;

    @Column(nullable = false)
    private String providerName;

    @Column(nullable = false)
    private String providerEmail;

    @Column(nullable = false)
    private String providerPhone;

    @Column(nullable = false)
    private String providerAddress;

    @Column(nullable = true)
    private String providerPerson;

    @Column(nullable = false)
    private ProviderType providerType;

    @Column (nullable = false)
    private Boolean enabled = true;
}
