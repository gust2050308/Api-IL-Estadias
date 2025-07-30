package com.labelinternational.apiinternationallabel.Controller;

import com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder.InkItemOrderDto;
import com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder.PurchaseOrderResponseDto;
import com.labelinternational.apiinternationallabel.Entity.InkItemOrder;
import com.labelinternational.apiinternationallabel.Entity.PurchaseOrder;
import com.labelinternational.apiinternationallabel.Service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/PurchaseOrder")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping
    public ResponseEntity<?> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.createPurchaseOrder(purchaseOrder);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable Long id) {
        return purchaseOrderService.getPurchaseOrderById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder, @PathVariable Long id) {
        return purchaseOrderService.updatePurchaseOrder(purchaseOrder, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PurchaseOrder> deletePurchaseOrder(@PathVariable Long id) {
        return purchaseOrderService.deletePurchaseOrder(id);
    }

    @GetMapping("/findIncompleteOrdersPaper")
    public ResponseEntity<List<PurchaseOrder>> findIncompleteOrdersPaper() {
        return purchaseOrderService.findIncompleteOrdersByMaterialTypePaper();
    }

    @GetMapping("/findIncompleteOrdersInk")
    public ResponseEntity<List<PurchaseOrderResponseDto>> findIncompleteOrdersInk() {
        return purchaseOrderService.findIncompleteOrdersByMaterialTypeInk();
    }

    @GetMapping("/findCompleteOrdersInk")
    public ResponseEntity<List<PurchaseOrder>> findCompleteOrdersInk() {
        return purchaseOrderService.findCompleteOrdersByMaterialTypeInk();
    }

    @GetMapping("/findCompleteOrdersPaper")
    public ResponseEntity<List<PurchaseOrder>> findCompleteOrdersPaper() {
        return purchaseOrderService.findCompleteOrdersByMaterialTypePaper();
    }

    @GetMapping("/findByNumber/{number}")
    public ResponseEntity<PurchaseOrder> findByNumber(@PathVariable Long number) {
        return purchaseOrderService.findOrderByNumber(number);
    }

    @GetMapping("/findItemsInsatisfied/{number}")
    public ResponseEntity<PurchaseOrderResponseDto> findItemsInsatisfied(@PathVariable Long number) {
        return purchaseOrderService.findItemsInsatifiedFromNumberOrder(number);
    }

    @PostMapping("/createOne")
    public ResponseEntity<?> createOne(@RequestBody InkItemOrder inkItemOrder) {
        return purchaseOrderService.sveOne(inkItemOrder);
    }

    @GetMapping("/ItemsByOrderNumber/{orderNumber}")
    private ResponseEntity<List<InkItemOrderDto>> ItemsByOrderNumber(@PathVariable Long orderNumber) {
        return purchaseOrderService.findItemsFromOrderNumber(orderNumber);
    }
}
