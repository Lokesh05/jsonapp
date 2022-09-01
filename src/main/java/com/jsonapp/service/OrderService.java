package com.jsonapp.service;

import com.jsonapp.model.*;
import com.jsonapp.repository.CustomerRepository;
import com.jsonapp.repository.OrderLinesRepository;
import com.jsonapp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLinesRepository orderLinesRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public void insertOrderDetails(OrderRequest orderRequest) {

        if (orderRequest != null) {

            List<OrderRequest.OrderLines> orderLinesList = orderRequest.getOrder_lines();
            Iterator<OrderRequest.OrderLines> it = orderLinesList.iterator();
            orderRepository.save(Order.builder()
                    .orderNumber(orderRequest.getOrder_number())
                    .orderDate(orderRequest.getOrder_date())
                    .build());
            log.info("Inserted the order record in order table");
            while (it.hasNext()) {
                OrderRequest.OrderLines orderLines = it.next();

                log.info("current request={}", orderLines.getLine_number());
                orderLinesRepository.save(OrderLines.builder()
                        .orderNumber(orderRequest.getOrder_number())
                        .lineNumber(orderLines.getLine_number())
                        .productId(orderLines.getProduct_id())
                        .quantity(orderLines.getQuantity())
                        .productDescription(orderLines.getProduct_description())
                        .build());
                log.info("Inserted the orderlines record in orderlines table");
            }
            customerRepository.save(Customer.builder()
                    .orderNumber(orderRequest.getOrder_number())
                    .customerId(orderRequest.getCustomer().getCustomer_id())
                    .customerName(orderRequest.getCustomer().getCustomer_name())
                    .address1(orderRequest.getCustomer().getAddress1())
                    .address2(orderRequest.getCustomer().getAddress2())
                    .city(orderRequest.getCustomer().getCity())
                    .state(orderRequest.getCustomer().getState())
                    .country(orderRequest.getCustomer().getCountry())
                    .build());
            log.info("Inserted the customer record in customer table");
        }
    }

    public OrderInfoResponse getOrderInfo(String order_number, String order_date, String customer_id) {

        OrderInfoResponse orderInfoResponse = new OrderInfoResponse();
        List<OrderInfoResponse.OrderLines> orderLinesInfo = new ArrayList<>();
        OrderInfoResponse.Customer customer = new OrderInfoResponse.Customer();

        if (!order_number.equals("empty") && !order_date.equals("empty") && !customer_id.equals("empty")) {

            Order orderDetail = orderRepository.fetchOrderDetails(order_number, order_date);

            log.info("Order Number={}, Order Date={}", orderDetail.getOrderNumber(), orderDetail.getOrderDate());

            List<OrderLines> orderLinesList = orderLinesRepository.fetchOrderLines(order_number);

            Customer customerDetail = customerRepository.fetchCustomerDetails(customer_id);

            orderLinesList.stream().forEach(
                    orderLines -> {
                        orderLinesInfo.add(OrderInfoResponse.OrderLines.builder()
                                .line_number(orderLines.getLineNumber())
                                .product_id(orderLines.getProductId())
                                .quantity(orderLines.getQuantity())
                                .product_description(orderLines.getProductDescription())
                                .build());
                    }
            );

            customer.setCustomer_id(customerDetail.getCustomerId());
            customer.setCustomer_name(customerDetail.getCustomerName());
            customer.setAddress1(customerDetail.getAddress1());
            customer.setAddress2(customerDetail.getAddress2());
            customer.setCity(customerDetail.getCity());
            customer.setState(customerDetail.getState());
            customer.setCountry(customerDetail.getCountry());

            if (orderLinesInfo.size() > 0) {
                orderInfoResponse.setOrder_number(orderDetail.getOrderNumber());
                orderInfoResponse.setOrder_date(orderDetail.getOrderDate());
                orderInfoResponse.setOrder_lines(orderLinesInfo);
                orderInfoResponse.setCustomer(customer);
                log.info("response for Order Info={}", orderInfoResponse);

                return orderInfoResponse;
            } else {

                return null;
            }
        } else {

            return null;
        }
    }
}
