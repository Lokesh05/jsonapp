package com.jsonapp.controller;

import com.jsonapp.model.OrderInfoResponse;
import com.jsonapp.model.OrderRequest;
import com.jsonapp.service.OrderService;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;

public class OrderControllerTest {

    private OrderService orderService;
    private OrderController orderController;

    @Before
    public void setup() {
        orderService = Mockito.mock(OrderService.class);
        orderController = new OrderController(orderService);
    }

    @Test
    public void testGetOrderInfo() throws Exception {
        val orderInfoResponse = (OrderInfoResponse) getOrderInfoResponse();
        Mockito.doReturn(orderInfoResponse).when(orderService).getOrderInfo(any(), any(), any());
        String order_number = "order-123";
        String order_date = "17-02-2022'T'16:04:31.421";
        String customer_id = "customer1";
        val response = orderController.getOrderDetail(order_number, order_date, customer_id);
        Assert.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testInsertOrderInfo() throws ParseException {
        Mockito.doNothing().when(orderService).insertOrderDetails(getOrderRequest());
        val response = orderController.insertOrderInfo(getOrderRequest());
        Mockito.verify(orderService, Mockito.times(1)).insertOrderDetails(any());
        Assert.assertEquals(201, response.getStatusCodeValue());
    }


    public OrderRequest getOrderRequest() {

        OrderRequest orderRequest = new OrderRequest();
        OrderRequest.OrderLines orderLines = new OrderRequest.OrderLines();
        OrderRequest.Customer customer = new OrderRequest.Customer();

        orderRequest.setOrder_number("order-123");
        orderRequest.setOrder_date("17-02-2022'T'16:04:31.421");
        orderLines.setLine_number("1");
        orderLines.setProduct_id("10002367");
        orderLines.setQuantity("2");
        orderLines.setProduct_description("T-shirt");
        orderRequest.setOrder_lines(Collections.singletonList(orderLines));

        customer.setCustomer_id("customer1");
        customer.setCustomer_name("name 1");
        customer.setAddress1("address line 1");
        customer.setAddress2("address line 2");
        customer.setCity("Bangalore");
        customer.setState("Karnataka");
        customer.setCountry("India");

        orderRequest.setCustomer(customer);

        return orderRequest;
    }

    public OrderInfoResponse getOrderInfoResponse() {

        OrderInfoResponse orderInfoResponse = new OrderInfoResponse();
        OrderInfoResponse.OrderLines orderLines = new OrderInfoResponse.OrderLines();
        OrderInfoResponse.Customer customer = new OrderInfoResponse.Customer();

        orderInfoResponse.setOrder_number("order-123");
        orderInfoResponse.setOrder_date("17-02-2022'T'16:04:31.421");
        orderLines.setLine_number("1");
        orderLines.setProduct_id("10002367");
        orderLines.setQuantity("2");
        orderLines.setProduct_description("T-shirt");
        orderInfoResponse.setOrder_lines(Collections.singletonList(orderLines));

        customer.setCustomer_id("customer1");
        customer.setCustomer_name("name 1");
        customer.setAddress1("address line 1");
        customer.setAddress2("address line 2");
        customer.setCity("Bangalore");
        customer.setState("Karnataka");
        customer.setCountry("India");

        orderInfoResponse.setCustomer(customer);

        return orderInfoResponse;
    }
}
