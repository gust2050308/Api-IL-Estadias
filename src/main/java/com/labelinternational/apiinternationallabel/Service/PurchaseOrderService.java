package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.Entity.PurchaseOrder;
import com.labelinternational.apiinternationallabel.Repository.PurchaseOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);

    public ResponseEntity<PurchaseOrder> createPurchaseOrder(PurchaseOrder purchaseOrder) {
        try {
            purchaseOrderRepository.save(purchaseOrder);
            return new ResponseEntity<>(purchaseOrder, HttpStatus.CREATED);
        }catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(Long id) {
        try {
            Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
            if(purchaseOrder.isPresent()) {
                return new ResponseEntity<>(purchaseOrder.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
        try {
            List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
            if(!purchaseOrders.isEmpty()) {
                return new ResponseEntity<>(purchaseOrders, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        try {
            Optional<PurchaseOrder> search = purchaseOrderRepository.findById(purchaseOrder.getId_PurchaseOrder());
            if (search.isPresent()) {
                PurchaseOrder purchaseOrderDB = search.get();
                purchaseOrder.setId_PurchaseOrder(purchaseOrderDB.getId_PurchaseOrder());
                purchaseOrderRepository.save(purchaseOrder);
                return new ResponseEntity<>(purchaseOrder, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<PurchaseOrder> deletePurchaseOrder(Long id) {
        try {
            Optional<PurchaseOrder> search = purchaseOrderRepository.findById(id);
            if (search.isPresent()) {
                purchaseOrderRepository.delete(search.get());
                return new ResponseEntity<>(search.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
