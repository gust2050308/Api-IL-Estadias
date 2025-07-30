package com.labelinternational.apiinternationallabel.Repository;

import com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder.InkItemOrderDto;
import com.labelinternational.apiinternationallabel.Entity.InkItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InkItemOrderRepository extends JpaRepository<InkItemOrder, Long> {

    @Query(value = "SELECT * FROM ink_item_order WHERE purchase_order_id = :purchase_order_id AND is_satisfied = false", nativeQuery = true)
    List<InkItemOrder> findItems(@Param("purchase_order_id") Long purchase_order_id);

    Optional<InkItemOrder> findById(Long idItemOrder);

    @Query("SELECT COUNT(*) FROM InkItemOrder i JOIN i.purchaseOrder p WHERE p.purchaseOrderNumber = :purchaseOrderNumber")
    Long totalItemsByOrder(@Param("purchaseOrderNumber") Long purchaseOrderNumber);

    @Query("SELECT COUNT(*) FROM InkItemOrder i JOIN i.purchaseOrder p WHERE p.purchaseOrderNumber = :purchaseOrderNumber AND i.isSatisfied = true")
    Long totalIncompleteItemsByOrder(@Param("purchaseOrderNumber") Long purchaseOrderNumber);

    @Query("""
                SELECT new com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder.InkItemOrderDto(
                            i.idItemOrder,
                            i.unitsQuantity,
                            i.amountKilograms,
                            i.codeItem,
                            i.totalUnitsQuantityArrived,
                            i.isSatisfied)
                FROM InkItemOrder i JOIN i.purchaseOrder p WHERE p.purchaseOrderNumber = :orderNumber AND i.isSatisfied = false
            """)
    List<InkItemOrderDto> ordersFromPurchaseOrderNumber(@Param("orderNumber") Long orderNumber);
}
