package com.labelinternational.apiinternationallabel.Repository;

import com.labelinternational.apiinternationallabel.DTOs.ProviderDtos.ProviderFormDto;
import com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder.ProviderDto;
import com.labelinternational.apiinternationallabel.Entity.Enums.ProviderType;
import com.labelinternational.apiinternationallabel.Entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    @Query("SELECT new com.labelinternational.apiinternationallabel.DTOs.ProviderDtos.ProviderFormDto(p.idProvider, p.providerName) FROM Provider p WHERE p.providerType = :provider_type AND p.enabled = true")
    List<ProviderFormDto> findByType(@Param("provider_type") ProviderType providerType);

    @Query("""
            SELECT new com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder.ProviderDto(
                        p.idProvider,
                        p.providerName,
                        p.providerAddress,
                        p.providerEmail,
                        p.providerPhone,
                        p.providerPerson,
                        p.providerType,
                        p.enabled) FROM Provider p WHERE p.providerType = :provider_type ORDER BY p.enabled DESC""")
    List<ProviderDto> findProviderByType(@Param("provider_type") ProviderType providerType);

}