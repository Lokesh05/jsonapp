package com.jsonapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@AllArgsConstructor
@Entity
@JsonInclude(NON_NULL)
@IdClass(OrderLinesPk.class)
@Table(name = "order_lines")
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderLines {

    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @Id
    @Column(name = "line_number", nullable = false)
    private String lineNumber;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "quantity", nullable = false)
    private String quantity;

    @Column(name = "product_description", nullable = false)
    private String productDescription;
}
