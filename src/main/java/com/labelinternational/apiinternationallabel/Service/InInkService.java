package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.DTOs.Entry.InkEntryDto;
import com.labelinternational.apiinternationallabel.DTOs.HistoryEntriesInk;
import com.labelinternational.apiinternationallabel.DTOs.InkItemOrderWithEntriesDto;
import com.labelinternational.apiinternationallabel.Mappers.InInkMapper;
import com.labelinternational.apiinternationallabel.Mappers.InkItemOrderMapper;
import com.labelinternational.apiinternationallabel.Mappers.PurchaseOrderMapper;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private InInkMapper inInkMapper;

    @Autowired
    private InkItemOrderMapper inkItemOrderMapper;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<?> createInInk(List<InkEntryDto> inInks) {
        try {
            for (InkEntryDto inInk : inInks) {
                InkItemOrder itemOrder = inkItemOrderRepository.findById(inInk.getIdItemOrder())
                        .orElseThrow(() -> new RuntimeException("InkItemOrder no encontrado"));

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

                itemOrder.setTotalUnitsQuantityArrived(
                        itemOrder.getTotalUnitsQuantityArrived().add(nuevo.getUnitsArrived())
                );

                itemOrder.setIsSatisfied(
                        itemOrder.getTotalUnitsQuantityArrived()
                                .compareTo(itemOrder.getUnitsQuantity()) >= 0
                );


                inInkRepository.save(nuevo);
                inkItemOrderRepository.save(itemOrder);

                Ink nuevaTinta = Ink.builder()
                        .inInk(nuevo)
                        .totalKilograms(nuevo.getUnitsArrived().multiply(itemOrder.getAmountKilograms()))
                        .volumeUsed(BigDecimal.ZERO)
                        .remainingVolume(nuevo.getUnitsArrived().multiply(itemOrder.getAmountKilograms()))
                        .build();
                inkRepository.save(nuevaTinta);
            }

            // obtener el primer item para ubicar la orden
            InkItemOrder firstItem = inkItemOrderRepository.findById(inInks.get(0).getIdItemOrder())
                    .orElseThrow(() -> new RuntimeException("Item no encontrado"));
            PurchaseOrder order = firstItem.getPurchaseOrder();

            // verificar si toda la orden está satisfecha
            boolean isOrderComplete = order.getInkItems().stream()
                    .allMatch(InkItemOrder::getIsSatisfied);

            if (isOrderComplete) {
                order.setIsComplete(true);
                purchaseOrderRepository.save(order);
            }

            // Mapea solo los InkItemOrder a DTO con entradas
            List<InkItemOrderWithEntriesDto> responseItems = order.getInkItems().stream()
                    .map(inkItemOrderMapper::toWithEntriesDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(responseItems);

        } catch (Exception e) {
            log.error("Error al crear InInk: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
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
    public ResponseEntity<List<HistoryEntriesInk>> getAllInInk() {
        try{
            List<InInk> inInks = inInkRepository.findAll();
            if(!inInks.isEmpty()) {
                List<HistoryEntriesInk> historyEntriesInks = new ArrayList<HistoryEntriesInk>();
                for (InInk inInk : inInks) {
                    HistoryEntriesInk entry = HistoryEntriesInk.builder()
                            .id(inInk.getIdInInk())
                            .date(inInk.getDateEntry())
                            .provider(inInk.getItemOrder().getPurchaseOrder().getProvider().getProvider_Name())
                            .invoiceRemission(inInk.getInvoiceRemission())
                            .orderNumber(inInk.getItemOrder().getPurchaseOrder().getPurchaseOrderNumber())
                            .typeMaterial(inInk.getTypeMaterial())
                            .code(inInk.getItemOrder().getCodeItem())
                            .units(inInk.getUnitsArrived())
                            .quantityKilograms(inInk.getItemOrder().getAmountKilograms())
                            .batchProvider(inInk.getBatchProvider())
                            .internalBatch(inInk.getInternalBatch())
                            .qualityCertificate(inInk.getQualityCertificate())
                            .build();
                    historyEntriesInks.add(entry);
                }
                return new ResponseEntity<>(historyEntriesInks, HttpStatus.OK);
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