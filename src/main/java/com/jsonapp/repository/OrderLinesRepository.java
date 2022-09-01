package com.jsonapp.repository;


import com.jsonapp.model.OrderLines;
import com.jsonapp.model.OrderLinesPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderLinesRepository extends JpaRepository<OrderLines, OrderLinesPk> {
    // all crud database methods

    @Query("select cc from OrderLines cc where cc.orderNumber in :orderNumber")
    List<OrderLines> fetchOrderLines(
            @Param("orderNumber") String orderNumber);
}
