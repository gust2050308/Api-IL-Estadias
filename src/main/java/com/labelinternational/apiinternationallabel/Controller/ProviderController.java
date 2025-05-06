package com.labelinternational.apiinternationallabel.Controller;

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

    @PutMapping
    public ResponseEntity<Provider> updateProvider(@RequestBody Provider provider) {
        return providerService.updateProvider(provider);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Provider> deleteProvider(@PathVariable Long id) {
        return providerService.deleteProvider(id);
    }
}
