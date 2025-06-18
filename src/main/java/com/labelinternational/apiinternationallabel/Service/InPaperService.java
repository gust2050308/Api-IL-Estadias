package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.Entity.InPaper;
import com.labelinternational.apiinternationallabel.Repository.InPaperRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InPaperService {

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);

    @Autowired
    InPaperRepository inPaperRepository;

    @Transactional
    public ResponseEntity<?> createInPaper(InPaper inPaper) {
        try {
            inPaperRepository.save(inPaper);
            return new ResponseEntity<>(inPaper, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.error("Error al crear InInk: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear InInk: {}", e.getMessage(), e);
            throw new RuntimeException("Error inesperado al crear InInk");
        }
    }

    @Transactional
    public ResponseEntity<InPaper> getInPaperById(Long id) {
        try{
            Optional<InPaper> inPaper = inPaperRepository.findById(id);
            if ( inPaper.isPresent()){
                return new ResponseEntity<>(inPaper.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<InPaper>> getAllInPaper() {
        try{
            List<InPaper> inPapers = inPaperRepository.findAll();
            if(!inPapers.isEmpty()){
                return new ResponseEntity<>(inPapers, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<InPaper> updateInPaper(InPaper inPaper) {
        try {
            Optional<InPaper> paperDb = inPaperRepository.findById(inPaper.getId());
            if (paperDb.isPresent()) {
                InPaper paper = paperDb.get();
                inPaperRepository.save(paper);
                return new ResponseEntity<>(paper, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<InPaper> deleteInPaper(Long id) {
        try {
            Optional<InPaper> paperDb = inPaperRepository.findById(id);
            if (paperDb.isPresent()) {
                inPaperRepository.delete(paperDb.get());
                return new ResponseEntity<>(paperDb.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<InPaper>> createSeveral(List<InPaper> inPapers){
        try {
            if(!inPapers.isEmpty()){
                inPaperRepository.saveAll(inPapers);
                return new ResponseEntity<>(inPapers, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
