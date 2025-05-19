package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.Entity.InkItemOrder;
import com.labelinternational.apiinternationallabel.Entity.PaperItemOrder;
import com.labelinternational.apiinternationallabel.Entity.PurchaseOrder;
import com.labelinternational.apiinternationallabel.Repository.InkItemOrderRepository;
import com.labelinternational.apiinternationallabel.Repository.PaperItemOrderRepository;
import com.labelinternational.apiinternationallabel.Repository.PurchaseOrderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private InkItemOrderRepository inkItemOrderRepository;

    @Autowired
    private PaperItemOrderRepository paperItemOrderRepository;

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);

    @Transactional
    public ResponseEntity<?> createPurchaseOrder(PurchaseOrder purchaseOrder) {
        try {
            Optional<List<PaperItemOrder>> paperInPurchaseOredr = Optional.ofNullable(purchaseOrder.getPaperItems());
            Optional<List<InkItemOrder>> inkInPurchaseOredr = Optional.ofNullable(purchaseOrder.getInkItems());
            if (paperInPurchaseOredr.isPresent() || inkInPurchaseOredr.isPresent()) {
                if (paperInPurchaseOredr.isPresent()) {

                    List<PaperItemOrder> paperItems = paperInPurchaseOredr.get();

                    purchaseOrder.setPaperItems(new ArrayList<>());
                    PurchaseOrder savedOrder = purchaseOrderRepository.save(purchaseOrder);

                    for (PaperItemOrder itemOrder : paperItems) {
                        itemOrder.setPurchaseOrder(savedOrder);
                        paperItemOrderRepository.save(itemOrder);
                    }
                    savedOrder.setPaperItems(paperItems);
                    purchaseOrderRepository.save(savedOrder);

                    return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
                }
                List<InkItemOrder> inkItems = inkInPurchaseOredr.get();

                purchaseOrder.setInkItems(new ArrayList<>());
                PurchaseOrder savedOrder = purchaseOrderRepository.save(purchaseOrder);

                for (InkItemOrder itemOrder : inkItems) {
                    itemOrder.setPurchaseOrder(savedOrder);
                    inkItemOrderRepository.save(itemOrder);
                }
                savedOrder.setInkItems(inkItems);
                purchaseOrderRepository.save(savedOrder);

                return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("La orden de compra debe tener al menos un Elemento", HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e) {
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
    public ResponseEntity<List<PurchaseOrder>> findIncompleteOrders(){
        try {
            List<PurchaseOrder> incompleteOrders = purchaseOrderRepository.findIncompleteOrders();
            if(!incompleteOrders.isEmpty()) {
                return new ResponseEntity<>(incompleteOrders, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
