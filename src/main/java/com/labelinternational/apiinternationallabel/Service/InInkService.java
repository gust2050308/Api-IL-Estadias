package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.Entity.InInk;
import com.labelinternational.apiinternationallabel.Entity.Ink;
import com.labelinternational.apiinternationallabel.Entity.Provider;
import com.labelinternational.apiinternationallabel.Entity.PurchaseOrder;
import com.labelinternational.apiinternationallabel.Repository.InInkRepository;
import com.labelinternational.apiinternationallabel.Repository.InkRepository;
import com.labelinternational.apiinternationallabel.Repository.ProviderRepository;
import com.labelinternational.apiinternationallabel.Repository.PurchaseOrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InInkService {


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private InInkRepository inInkRepository;

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);

    @Autowired
    private InkRepository inkRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<?> createInInk(InInk inInk) {
        try {/*
            // Verificar y cargar entidades relacionadas
            Provider provider = providerRepository.findById(inInk.getProvider().getId_Provider())
                    .orElseThrow(() -> new RuntimeException("Provider not found"));

            PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(inInk.getPurchaseOrder().getId_PurchaseOrder())
                    .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

            // Asignar entidades gestionadas
            inInk.setProvider(provider);
            inInk.setPurchaseOrder(purchaseOrder);*/

            // Guardar entidad InInk
            inInkRepository.save(inInk);
            return new ResponseEntity<>(inInk, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            log.error("Error al crear InInk: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear InInk: {}", e.getMessage(), e);
            throw new RuntimeException("Error inesperado al crear InInk");
        }
    }

    /*@Transactional
    public ResponseEntity<?> createInInk(InInk inInk) {
        try {
            // Asegúrate de que las entidades relacionadas estén gestionadas
            PurchaseOrder purchaseOrder = entityManager.contains(inInk.getPurchaseOrder())
                    ? inInk.getPurchaseOrder()
                    : entityManager.merge(inInk.getPurchaseOrder());
            inInk.setPurchaseOrder(purchaseOrder);

            Provider provider = entityManager.contains(inInk.getProvider())
                    ? inInk.getProvider()
                    : entityManager.merge(inInk.getProvider());
            inInk.setProvider(provider);

            // Guardar la entidad gestionada
            InInk savedInInk = inInkRepository.save(inInk);
            return new ResponseEntity<>(savedInInk, HttpStatus.CREATED);

        } catch (ObjectOptimisticLockingFailureException e) {
            log.error("Error de concurrencia: {}", e.getMessage(), e);
            return new ResponseEntity<>("Error de concurrencia. Intenta nuevamente.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error("Error al crear InInk: {}", e.getMessage(), e);
            return new ResponseEntity<>("Ocurrió un error, verifica los datos.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    @Transactional
    public  ResponseEntity<InInk> getInInkById(Long id) {
        try{
            Optional<InInk> inInk = inInkRepository.findById(id);
            if(inInk.isPresent()) {
                return new ResponseEntity<>(inInk.get(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<InInk>> getAllInInk() {
        try{
            List<InInk> inInks = inInkRepository.findAll();
            if(!inInks.isEmpty()) {
                return new ResponseEntity<>(inInks, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<InInk> updateInInk(InInk inInk) {
        try {
            Optional<InInk> search = inInkRepository.findById(inInk.getId_Ink());
            if(search.isPresent()) {
                InInk updatedInInk = search.get();
                inInk.setId_Ink(updatedInInk.getId_Ink());
                inInkRepository.save(inInk);
                return new ResponseEntity<>(inInk, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional
    public ResponseEntity<InInk> deleteInInk(Long id) {
        try {
            Optional<InInk> search = inInkRepository.findById(id);
            if(search.isPresent()) {
                inInkRepository.delete(search.get());
                return new ResponseEntity<>(search.get(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}