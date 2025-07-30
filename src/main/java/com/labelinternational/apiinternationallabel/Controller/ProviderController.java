package com.labelinternational.apiinternationallabel.Controller;

import com.labelinternational.apiinternationallabel.DTOs.ProviderDtos.ProviderFormDto;
import com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder.ProviderDto;
import com.labelinternational.apiinternationallabel.Entity.Enums.ProviderType;
import com.labelinternational.apiinternationallabel.Entity.Provider;
import com.labelinternational.apiinternationallabel.Service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping
    public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
        return providerService.createProvider(provider);
    }

    @GetMapping
    public ResponseEntity<List<Provider>> getAllProviders() {
        return providerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable Long id) {
        return providerService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable Long id,  @RequestBody Provider provider) {
        return providerService.updateProvider(id, provider);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Provider> deleteProvider(@PathVariable Long id) {
        return providerService.deleteProvider(id);
    }

    @PostMapping("/ProvidersByType")
    public ResponseEntity<List<ProviderFormDto>> getProvidersByType(@RequestBody ProviderType providerType) {
        return providerService.providerFormDto(providerType);
    }

    @PostMapping("/disableProvider/{id}")
    public ResponseEntity<ProviderDto> disableProvider(@PathVariable Long id) {
        return providerService.disableProvider(id);
    }

    @GetMapping("/findProvidersByType/{providerType}")
    public ResponseEntity<List<ProviderDto>> findProvidersByType(@PathVariable ProviderType providerType) {
        return providerService.providersByTypeDesc(providerType);
    }
}