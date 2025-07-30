package com.labelinternational.apiinternationallabel.Repository;

import com.labelinternational.apiinternationallabel.Entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query(value = "SELECT * FROM purchase_order WHERE purchase_order_number = :purchaseOrderNumber", nativeQuery = true)
    Optional<PurchaseOrder> findByPurchaseOrderNumberNativeQuery(@Param("purchaseOrderNumber") Long purchaseOrderNumber);

    @Query("SELECT P FROM PurchaseOrder P WHERE P.isComplete = FALSE")
    List<PurchaseOrder> findIncompleteOrders();

    @Query(value = "SELECT * FROM purchase_order WHERE is_complete = false AND type_material = false" , nativeQuery = true)
    List<PurchaseOrder> findIncompleteOrdersByMaterialTypePaper();

    @Query(value = "SELECT * FROM purchase_order WHERE is_complete = false AND type_material = true" , nativeQuery = true)
    List<PurchaseOrder> findIncompleteOrdersByMaterialTypeInk();

    @Query(value = "SELECT * FROM purchase_order WHERE is_complete = true AND type_material = false" , nativeQuery = true)
    List<PurchaseOrder> findCompleteOrdersByMaterialTypePaper();

    @Query(value = "SELECT * FROM purchase_order WHERE is_complete = true AND type_material = true" , nativeQuery = true)
    List<PurchaseOrder> findCompleteOrdersByMaterialTypeInk();

    @Query("SELECT po FROM PurchaseOrder po " +
            "LEFT JOIN FETCH po.inkItems i " +
            "WHERE po.purchaseOrderNumber = :orderNumber " +
            "AND (i IS NULL OR i.isSatisfied = false)")
    Optional<PurchaseOrder> findByPurchaseOrderNumberWithUnsatisfiedItems(@Param("orderNumber") Long orderNumber);
}
