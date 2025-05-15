package com.labelinternational.apiinternationallabel.Repository;

import com.labelinternational.apiinternationallabel.Entity.PurchaseOrder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    PurchaseOrder findByPurchaseOrderNumber(Long purchaseOrderNumber);

    /*
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO purchase_order (purchase_order_number, provider_id_provider, purchase_date, required_by, payment_method, delivery_date, shipment, delivery_place ) VALUES (:purchaseOrderNumber, :provider, :purchaseDate, :requiredBy, :paymentMethod, :deliveryDate, :shipment, :deliveryPlace)", nativeQuery = true)
    void insertPurchaseOrder(@Param("purchaseOrderNumber") String purchaseOrderNumber, @Param("provider")  Long provider, @Param("purchaseDate") Date purchaseDate, @Param("requiredBy")  String requiredBy, @Param("paymentMethod") String paymentMethod, @Param("deliveryDate") Date deliveryDate, @Param("shipment") String shipment, @Param("deliveryPlace") String deliveryPlace);
*/


}
