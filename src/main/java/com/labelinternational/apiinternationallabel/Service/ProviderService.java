package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.Entity.Provider;
import com.labelinternational.apiinternationallabel.Repository.ProviderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);


    @Transactional
    public ResponseEntity<Provider> createProvider(Provider provider) {
        try{
            providerRepository.save(provider);
            return new ResponseEntity<>(provider, HttpStatus.CREATED);
        }catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Provider> findById(Long id) {
        try{
            Optional<Provider> provider = providerRepository.findById(id);
            if(provider.isPresent()){
                return new ResponseEntity<>(provider.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<Provider>> findAll() {
        try{
            List<Provider> providers = providerRepository.findAll();
            if(!providers.isEmpty()){
                return new ResponseEntity<>(providers, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Provider> updateProvider(Provider provider) {
        try{
            Optional<Provider> searchProvider = providerRepository.findById(provider.getId_Provider());
            if(searchProvider.isPresent()){
                Provider updateProvider = searchProvider.get();
                provider.setId_Provider(updateProvider.getId_Provider());
                providerRepository.save(provider);
                return new ResponseEntity<>(provider, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Provider> deleteProvider(Long id) {
        try{
            Optional<Provider> searchProvider = providerRepository.findById(id);
            if(searchProvider.isPresent()){
                providerRepository.deleteById(id);
                return new ResponseEntity<>(searchProvider.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
