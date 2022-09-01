package com.jsonapp.repository;

import com.jsonapp.model.Customer;
import com.jsonapp.model.CustomerPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, CustomerPk> {

    // all crud database methods

    @Query("select cc from Customer cc where cc.customerId in :customerId")
    Customer fetchCustomerDetails(
            @Param("customerId") String customerId);
}
