package com.labelinternational.apiinternationallabel.Repository;

import com.labelinternational.apiinternationallabel.Entity.InkItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InkItemOrderRepository extends JpaRepository<InkItemOrder, Long> {

    @Query(value = "SELECT * FROM item_order WHERE purchase_orderid  = :purchaseOrderID", nativeQuery = true)
    List<InkItemOrder> findItems(@Param("purchaseOrderID") Long purchaseOrderID);


}
