package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.Entity.Ink;
import com.labelinternational.apiinternationallabel.Repository.InkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InkService {

    @Autowired
    private InkRepository inkRepository;

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);

    public ResponseEntity<Ink> createInk(Ink ink) {
        try{
            inkRepository.save(ink);
            return new ResponseEntity<>(ink, HttpStatus.CREATED);
        }catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Ink> findInkById(Long id) {
        try{ Optional<Ink> ink = inkRepository.findById(id);
            if(ink.isPresent()){
                return new ResponseEntity<>(ink.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Ink>> findAllInk() {
        try {
            List<Ink> inks = inkRepository.findAll();
            if(!inks.isEmpty()){
                return new ResponseEntity<>(inks, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Ink> updateInk(Ink ink) {
        try {
            Optional<Ink> search = inkRepository.findById(ink.getId_ink());
            if(search.isPresent()){
                Ink ink1 = search.get();
                ink.setId_ink(ink1.getId_ink());
                inkRepository.save(ink);
                return new ResponseEntity<>(ink, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Ink> deleteInkById(Long id) {
        Optional<Ink> ink = inkRepository.findById(id);
        if(ink.isPresent()){
            inkRepository.delete(ink.get());
            return new ResponseEntity<>(ink.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
