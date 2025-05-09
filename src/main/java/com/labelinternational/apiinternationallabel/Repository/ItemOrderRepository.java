package com.labelinternational.apiinternationallabel.Repository;

import com.labelinternational.apiinternationallabel.Entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {

    @Query(value = "SELECT * FROM item_order WHERE purchase_orderid  = :purchaseOrderID", nativeQuery = true)
    List<ItemOrder> findItems(@Param("purchaseOrderID") Long purchaseOrderID);


}
