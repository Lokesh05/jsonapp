package com.jsonapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    private String order_number;

    private String order_date;

    private List<OrderLines> order_lines;

    @Data
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderLines {

        private String line_number;
        private String product_id;
        private String quantity;
        private String product_description;

    }

    private Customer customer;

    @Data
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Customer {

        private String customer_id;
        private String customer_name;
        private String address1;
        private String address2;
        private String city;
        private String state;
        private String country;
    }

}
