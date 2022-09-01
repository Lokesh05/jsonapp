package com.jsonapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@AllArgsConstructor
@Entity
@JsonInclude(NON_NULL)
@IdClass(OrderPk.class)
@Table(name = "order_detail")
@NoArgsConstructor
@Builder(toBuilder = true)
public class Order {

    @Id
    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @Column(name = "order_date", nullable = false)
    private String orderDate;


}
