package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.DTOs.ProviderDtos.ProviderFormDto;
import com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder.ProviderDto;
import com.labelinternational.apiinternationallabel.Entity.Enums.ProviderType;
import com.labelinternational.apiinternationallabel.Entity.Provider;
import com.labelinternational.apiinternationallabel.Mappers.ProviderMapper;
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

    @Autowired
    private ProviderMapper providerMapper;

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
    public ResponseEntity<Provider> updateProvider(Long id, Provider provider) {
        try{
            Optional<Provider> searchProvider = providerRepository.findById(id);
            if(searchProvider.isPresent()){
                Provider updateProvider = searchProvider.get();
                    updateProvider.setProviderName(provider.getProviderName());
                    updateProvider.setProviderType(provider.getProviderType());
                    updateProvider.setProviderAddress(provider.getProviderAddress());
                    updateProvider.setProviderPhone(provider.getProviderPhone());
                    updateProvider.setProviderEmail(provider.getProviderEmail());
                    updateProvider.setProviderPerson(provider.getProviderPerson());
                providerRepository.save(updateProvider);
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

    @Transactional
    public ResponseEntity<ProviderDto> disableProvider(Long id) {
        try{
            Optional<Provider> searchProvider = providerRepository.findById(id);
            if(searchProvider.isPresent()){
                Provider provider = searchProvider.get();
                provider.setEnabled(!provider.getEnabled());
                providerRepository.save(provider);
                return new ResponseEntity<ProviderDto>(providerMapper.toDto(provider), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<ProviderFormDto>> providerFormDto(ProviderType providerType) {
        try {
            List<ProviderFormDto> listProviders  = providerRepository.findByType(providerType);
            if (!listProviders.isEmpty()) {
                return new ResponseEntity<>(listProviders, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional
    public ResponseEntity<List<ProviderDto>> providersByTypeDesc(ProviderType providerType) {
        try{
            List<ProviderDto> responseList = providerRepository.findProviderByType(providerType);
            if (!responseList.isEmpty()) {
                return new ResponseEntity<>(responseList, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
