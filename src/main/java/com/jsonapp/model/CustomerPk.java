package com.jsonapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CustomerPk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "customer_id", nullable = false)
    private String customerId;
}
