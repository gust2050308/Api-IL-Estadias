package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.Entity.PaperItemOrder;
import com.labelinternational.apiinternationallabel.Repository.PaperItemOrderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaperItemOrderService {

    @Autowired
    private PaperItemOrderRepository itemOrderRepository;

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);

    @Transactional
    public ResponseEntity<PaperItemOrder> createItemOrder(PaperItemOrder itemOrder) {
        try{
            itemOrderRepository.save(itemOrder);
            return new ResponseEntity<>(itemOrder, HttpStatus.CREATED);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
