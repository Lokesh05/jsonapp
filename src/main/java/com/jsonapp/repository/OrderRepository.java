package com.jsonapp.repository;

import com.jsonapp.model.Order;
import com.jsonapp.model.OrderPk;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderPk> {

    // all crud database methods

    @Query("select cc from Order cc where cc.orderNumber in :orderNumber and cc.orderDate in :orderDate")
    Order fetchOrderDetails(
            @Param("orderNumber") String orderNumber, @Param("orderDate") String orderDate);
}
