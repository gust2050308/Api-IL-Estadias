package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.DTOs.PurchaseOreder.PurchaseOrderResponseDto;
import com.labelinternational.apiinternationallabel.Mappers.PurchaseOrderMapper;
import com.labelinternational.apiinternationallabel.Entity.InkItemOrder;
import com.labelinternational.apiinternationallabel.Entity.PaperItemOrder;
import com.labelinternational.apiinternationallabel.Entity.Provider;
import com.labelinternational.apiinternationallabel.Entity.PurchaseOrder;
import com.labelinternational.apiinternationallabel.Repository.InkItemOrderRepository;
import com.labelinternational.apiinternationallabel.Repository.PaperItemOrderRepository;
import com.labelinternational.apiinternationallabel.Repository.ProviderRepository;
import com.labelinternational.apiinternationallabel.Repository.PurchaseOrderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private InkItemOrderRepository inkItemOrderRepository;

    @Autowired
    private PaperItemOrderRepository paperItemOrderRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);


    @Transactional
    public ResponseEntity<?> createPurchaseOrder(PurchaseOrder purchaseOrder) {
        try {
            List<InkItemOrder> inkItems = purchaseOrder.getInkItems();
            List<PaperItemOrder> paperItems = purchaseOrder.getPaperItems();

            if ((inkItems == null || inkItems.isEmpty()) && (paperItems == null || paperItems.isEmpty())) {
                return new ResponseEntity<>("La orden de compra debe tener al menos un elemento", HttpStatus.NOT_ACCEPTABLE);
            }

            Provider existingProvider = providerRepository.findById(purchaseOrder.getProvider().getId_Provider())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
            purchaseOrder.setProvider(existingProvider);

            purchaseOrder.setInkItems(null);
            purchaseOrder.setPaperItems(null);
            PurchaseOrder savedOrder = purchaseOrderRepository.save(purchaseOrder);

            if (inkItems != null) {
                for (InkItemOrder item : inkItems) {
                    item.setTotalUnitsQuantityArrived(BigDecimal.ZERO);
                    item.setPurchaseOrder(savedOrder);
                }
                savedOrder.setInkItems(inkItems);
            }

            if (paperItems != null) {
                for (PaperItemOrder item : paperItems) {
                    item.setPurchaseOrder(savedOrder);
                }
                savedOrder.setPaperItems(paperItems);
            }

            PurchaseOrder finalSavedOrder = purchaseOrderRepository.save(savedOrder);

            return new ResponseEntity<>(finalSavedOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error al crear la orden de compra: {}", e.getMessage(), e);
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

    @Transactional
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


    @Transactional
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(PurchaseOrder purchaseOrderUpdate, Long id) {
        try {
            Optional<PurchaseOrder> search = purchaseOrderRepository.findById(id);
            if (search.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            PurchaseOrder purchaseOrderDB = search.get();

            purchaseOrderDB.setPurchaseOrderNumber(purchaseOrderUpdate.getPurchaseOrderNumber());
            purchaseOrderDB.setProvider(purchaseOrderUpdate.getProvider());
            purchaseOrderDB.setRequestDate(purchaseOrderUpdate.getRequestDate());
            purchaseOrderDB.setDeliveryDateExpected(purchaseOrderUpdate.getDeliveryDateExpected());
            purchaseOrderDB.setDateDelivered(purchaseOrderUpdate.getDateDelivered());
            purchaseOrderDB.setRequiredBy(purchaseOrderUpdate.getRequiredBy());
            purchaseOrderDB.setPaymentMethod(purchaseOrderUpdate.getPaymentMethod());
            purchaseOrderDB.setShipment(purchaseOrderUpdate.getShipment());
            purchaseOrderDB.setDeliveryPlace(purchaseOrderUpdate.getDeliveryPlace());

            if (Boolean.FALSE.equals(purchaseOrderDB.getTypeMaterial())) {
                List<PaperItemOrder> updatedItems = purchaseOrderUpdate.getPaperItems();
                List<PaperItemOrder> currentItems = purchaseOrderDB.getPaperItems();

                for (PaperItemOrder current : currentItems) {
                    if (updatedItems.stream().noneMatch(i -> i.getIdPaperItemOrder() != null && i.getIdPaperItemOrder().equals(current.getIdPaperItemOrder()))) {
                        paperItemOrderRepository.deleteById(current.getIdPaperItemOrder());
                    }
                }

                for (PaperItemOrder item : updatedItems) {
                    item.setPurchaseOrder(purchaseOrderDB);
                    paperItemOrderRepository.save(item);
                }

                purchaseOrderDB.setPaperItems(updatedItems);

            } else {
                List<InkItemOrder> updatedItems = purchaseOrderUpdate.getInkItems();
                List<InkItemOrder> currentItems = purchaseOrderDB.getInkItems();

                for (InkItemOrder current : currentItems) {
                    if (updatedItems.stream().noneMatch(i -> i.getIdItemOrder() != null && i.getIdItemOrder().equals(current.getIdItemOrder()))) {
                        inkItemOrderRepository.deleteById(current.getIdItemOrder());
                    }
                }

                for (InkItemOrder item : updatedItems) {
                    item.setPurchaseOrder(purchaseOrderDB);
                    inkItemOrderRepository.save(item);
                }

                purchaseOrderDB.setInkItems(updatedItems);
            }

            PurchaseOrder saved = purchaseOrderRepository.save(purchaseOrderDB);
            return new ResponseEntity<>(saved, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error actualizando orden de compra: {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional
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

    @Transactional
    public ResponseEntity<List<PurchaseOrder>> findIncompleteOrdersByMaterialTypePaper(){
        try {
            List<PurchaseOrder> incompleteOrders = purchaseOrderRepository.findIncompleteOrdersByMaterialTypePaper();
            if(!incompleteOrders.isEmpty()) {
                return new ResponseEntity<>(incompleteOrders, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<PurchaseOrderResponseDto>> findIncompleteOrdersByMaterialTypeInk(){
        try {
            List<PurchaseOrder> incompleteOrders = purchaseOrderRepository.findIncompleteOrdersByMaterialTypeInk();

            if (!incompleteOrders.isEmpty()) {
                List<PurchaseOrderResponseDto> dtoList = purchaseOrderMapper.toResponseDtoList(incompleteOrders);
                return ResponseEntity.ok(dtoList);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error buscando órdenes incompletas: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @Transactional
    public ResponseEntity<List<PurchaseOrder>> findCompleteOrdersByMaterialTypeInk(){
        try {
            List<PurchaseOrder> incompleteOrders = purchaseOrderRepository.findCompleteOrdersByMaterialTypeInk();
            if(!incompleteOrders.isEmpty()) {
                return new ResponseEntity<>(incompleteOrders, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<PurchaseOrder>> findCompleteOrdersByMaterialTypePaper(){
        try {
            List<PurchaseOrder> incompleteOrders = purchaseOrderRepository.findCompleteOrdersByMaterialTypePaper();
            if(!incompleteOrders.isEmpty()) {
                return new ResponseEntity<>(incompleteOrders, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<PurchaseOrder> findOrderByNumber(Long number) {
        try {
            Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findByPurchaseOrderNumberNativeQuery(number);
            log.info(purchaseOrder.get().getPurchaseOrderNumber().toString());
            if (purchaseOrder.isPresent()) {
                return new ResponseEntity<>(purchaseOrder.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<PurchaseOrderResponseDto> findItemsInsatifiedFromNumberOrder(Long orderNumber) {
        try{
            Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findByPurchaseOrderNumberWithUnsatisfiedItems(orderNumber);
            PurchaseOrderResponseDto responseDto = purchaseOrderMapper.toResponseDto(purchaseOrder.get());
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<InkItemOrder> sveOne(InkItemOrder inkItemOrder) {
        try{
           inkItemOrderRepository.save(inkItemOrder);
           return new ResponseEntity<>(inkItemOrder, HttpStatus.CREATED);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

