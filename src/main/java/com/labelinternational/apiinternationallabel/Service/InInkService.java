package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.Entity.InInk;
import com.labelinternational.apiinternationallabel.Repository.InInkRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InInkService {

    @Autowired
    private InInkRepository inInkRepository;

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<?> createInInk(InInk inInk) {
        try {
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
            }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}