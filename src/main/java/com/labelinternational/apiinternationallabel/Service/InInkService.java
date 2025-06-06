package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.Controller.DTOs.InkEntryDto;
import com.labelinternational.apiinternationallabel.Entity.InInk;
import com.labelinternational.apiinternationallabel.Entity.Ink;
import com.labelinternational.apiinternationallabel.Entity.InkItemOrder;
import com.labelinternational.apiinternationallabel.Entity.PurchaseOrder;
import com.labelinternational.apiinternationallabel.Repository.InInkRepository;
import com.labelinternational.apiinternationallabel.Repository.InkItemOrderRepository;
import com.labelinternational.apiinternationallabel.Repository.InkRepository;
import com.labelinternational.apiinternationallabel.Repository.PurchaseOrderRepository;
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
    private InkRepository inkRepository;

    @Autowired
    private InInkRepository inInkRepository;

    @Autowired
    private InkItemOrderRepository inkItemOrderRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<?> createInInk(List<InkEntryDto> inInks) {
        try {
            for (InkEntryDto inInk : inInks) {
                Optional<InkItemOrder> itemOrderDB = inkItemOrderRepository.findById(inInk.getIdItemOrder());

                InkItemOrder itemOrder = itemOrderDB.get();

                InInk nuevo = InInk.builder()
                        .dateEntry(inInk.getInkEntry().getDateEntry())
                        .invoiceRemission(inInk.getInkEntry().getInvoiceRemission())
                        .typeMaterial(inInk.getInkEntry().getTypeMaterial())
                        .batchProvider(inInk.getInkEntry().getBatchProvider())
                        .internalBatch(inInk.getInkEntry().getInternalBatch())
                        .unitsArrived(inInk.getInkEntry().getUnitsArrived())
                        .qualityCertificate(inInk.getInkEntry().getQualityCertificate())
                        .itemOrder(itemOrder)
                        .build();

                itemOrder.setTotalUnitsQuantityArrived(itemOrder.getTotalUnitsQuantityArrived() + nuevo.getUnitsArrived());

                if(itemOrder.getTotalUnitsQuantityArrived() >= itemOrder.getUnitsQuantity()){
                    itemOrder.setIsSatisfied(true);
                }

                inInkRepository.save(nuevo);
                inkItemOrderRepository.save(itemOrder);

                Ink nuevaTinta = Ink.builder()
                        .inInk(nuevo)
                        .totalKilograms(nuevo.getUnitsArrived() * itemOrder.getAmountKilograms())
                        .volumeUsed(0L)
                        .remainingVolume(nuevo.getUnitsArrived() * itemOrder.getAmountKilograms())
                        .build();

                inkRepository.save(nuevaTinta);
            }

            Optional<InkItemOrder> itemTemp0 = inkItemOrderRepository.findById(inInks.get(0).getIdItemOrder());
            InkItemOrder itemTemp = itemTemp0.get();
            Optional<PurchaseOrder> orderTemp = purchaseOrderRepository.findByPurchaseOrderNumberNativeQuery(itemTemp.getPurchaseOrder().getPurchaseOrderNumber());
            if (orderTemp.isPresent()) {
                PurchaseOrder orderTemp2 = orderTemp.get();
                List<InkItemOrder> listToCheck = orderTemp.get().getInkItems();
                boolean flag = false;

                for(InkItemOrder order : listToCheck ){
                    if(order.getIsSatisfied()){
                        flag = true;
                    }else {
                        flag = false;
                        break;
                    }
                }

                if(flag){
                    orderTemp2.setIsComplete(true);
                    purchaseOrderRepository.save(orderTemp2);
                }
            }

            return new ResponseEntity<>(orderTemp, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error inesperado al crear InInk: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
            Optional<InInk> search = inInkRepository.findById(inInk.getIdInInk());
            if(search.isPresent()) {
                InInk updatedInInk = search.get();
                inInk.setIdInInk(updatedInInk.getIdInInk());
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

    @Transactional
    public ResponseEntity<PurchaseOrder> createInputsFromOrder(List<InkItemOrder> inkItemOrders, Long id){
        return null;
    }

}