package com.labelinternational.apiinternationallabel.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.Query;

@Entity
public class Provider {

    @Id
    @GeneratedValue
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

    public void setId_Provider(Long idProvider) {
        this.id_Provider = idProvider;
    }

    public Long getId_Provider() {
        return id_Provider;
    }

    public String getProvider_Name() {
        return Provider_Name;
    }

    public String getProvider_Email() {
        return Provider_Email;
    }

    public String getProvider_Phone() {
        return Provider_Phone;
    }

    public String getProvider_Address() {
        return Provider_Address;
    }

    public String getProvider_Person() {
        return Provider_Person;
    }

    public void setProvider_Name(String provider_Name) {
        Provider_Name = provider_Name;
    }

    public void setProvider_Email(String provider_Email) {
        Provider_Email = provider_Email;
    }

    public void setProvider_Phone(String provider_Phone) {
        Provider_Phone = provider_Phone;
    }

    public void setProvider_Person(String provider_Person) {
        Provider_Person = provider_Person;
    }

    public void setProvider_Address(String provider_Address) {
        Provider_Address = provider_Address;
    }
}
