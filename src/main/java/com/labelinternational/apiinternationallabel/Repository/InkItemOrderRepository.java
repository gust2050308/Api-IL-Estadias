package com.labelinternational.apiinternationallabel.Repository;

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
}
