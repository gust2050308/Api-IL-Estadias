package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.Entity.ItemOrder;
import com.labelinternational.apiinternationallabel.Entity.PurchaseOrder;
import com.labelinternational.apiinternationallabel.Repository.ItemOrderRepository;
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

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ItemOrderRepository itemOrderRepository;

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);

    public ResponseEntity<?> createPurchaseOrder(PurchaseOrder purchaseOrder) {
        try {
            // Verificar si la lista de ítems no está vacía o nula
            if (purchaseOrder.getItems() == null || purchaseOrder.getItems().isEmpty()) {
                return new ResponseEntity<>("La orden de compra debe tener al menos un Elemento", HttpStatus.NOT_ACCEPTABLE);
            }

            List<ItemOrder> items = purchaseOrder.getItems();

            purchaseOrder.setItems(null);
            purchaseOrderRepository.save(purchaseOrder);
            log.info("LLEGA AQUI");


            PurchaseOrder newPurchaseOrder = purchaseOrderRepository.findByPurchaseOrderNumber(purchaseOrder.getPurchaseOrderNumber());
            log.info(purchaseOrder.getId_PurchaseOrder().toString());
            for (ItemOrder itemOrder : items) {
                itemOrder.setPurchaseOrderID(newPurchaseOrder.getId_PurchaseOrder());
            }

            purchaseOrderRepository.save(newPurchaseOrder);
            return new ResponseEntity<>(purchaseOrder, HttpStatus.CREATED);

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
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        try {
            Optional<PurchaseOrder> search = purchaseOrderRepository.findById(purchaseOrder.getId_PurchaseOrder());
            if (!search.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            PurchaseOrder purchaseOrderDB = search.get();

            purchaseOrderDB.setPurchaseOrderNumber(purchaseOrder.getPurchaseOrderNumber());
            purchaseOrderDB.setPurchaseDate(purchaseOrder.getPurchaseDate());
            purchaseOrderDB.setProvider(purchaseOrder.getProvider());
            purchaseOrderDB.setPurchaseDate(purchaseOrder.getPurchaseDate());
            purchaseOrderDB.setRequiredBy(purchaseOrder.getRequiredBy());
            purchaseOrderDB.setPaymentMethod(purchaseOrder.getPaymentMethod());
            purchaseOrderDB.setDeliveryDate(purchaseOrder.getDeliveryDate());
            purchaseOrderDB.setShipment(purchaseOrder.getShipment());
            purchaseOrderDB.setDeliveryPlace(purchaseOrder.getDeliveryPlace());

            if (purchaseOrder.getItems() != null) {
                List<ItemOrder> existingItems = purchaseOrderDB.getItems();

                List<ItemOrder> updatedItems = List.of();

                for (ItemOrder newItem : purchaseOrder.getItems()) {
                    if (newItem.getId_ItemOrder() == null) {
                        newItem.setPurchaseOrderID(purchaseOrderDB.getId_PurchaseOrder());
                        updatedItems.add(newItem);
                    } else {
                        boolean exists = false;
                        for (ItemOrder existingItem : existingItems) {
                            if (existingItem.getId_ItemOrder().equals(newItem.getId_ItemOrder())) {
                                existingItem.setAmount(newItem.getAmount());
                                existingItem.setCodeItem(newItem.getCodeItem());
                                existingItem.setItem(newItem.getItem());

                                updatedItems.add(existingItem);
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            newItem.setPurchaseOrderID(purchaseOrderDB.getId_PurchaseOrder());
                            updatedItems.add(newItem);
                        }
                    }
                }
                existingItems.clear();
                existingItems.addAll(updatedItems);
            }


            PurchaseOrder updatedOrder = purchaseOrderRepository.save(purchaseOrderDB);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error al actualizar   la orden de compra: {}", e.getMessage(), e);
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
