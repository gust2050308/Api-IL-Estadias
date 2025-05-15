package com.labelinternational.apiinternationallabel.Entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_Provider;

    @Column(nullable = false)
    private String Provider_Name;

    @Column(nullable = false)
    private String Provider_Email;

    @Column(nullable = false)
    private String Provider_Phone;

    @Column(nullable = false)
    private String Provider_Address;

    @Column(nullable = true)
    private String Provider_Person;

}
