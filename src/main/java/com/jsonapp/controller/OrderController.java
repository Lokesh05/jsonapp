package com.jsonapp.controller;

import com.jsonapp.model.OrderInfoRequest;
import com.jsonapp.model.OrderInfoResponse;
import com.jsonapp.model.OrderRequest;
import com.jsonapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @RequestMapping(value = "/api/order/v1", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> insertOrderInfo(@RequestBody OrderRequest orderRequest) throws ParseException {
        log.info("event=insertOrder Received request for inserting an order. insertRequest= {}", orderRequest);
        orderService.insertOrderDetails(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/api/order/v1", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrderDetail(@RequestParam(defaultValue = "empty") String order_number,
                                            @RequestParam(defaultValue = "empty") String order_date,
                                            @RequestParam(defaultValue = "empty") String customer_id) throws Exception {
        log.info("Received request for fetching  order info for order_number= {}, order_date={}, customer_id={}",
                order_number, order_date, customer_id);

        if(order_number != null && order_date != null && customer_id != null) {

            OrderInfoResponse orderInfoResponse = orderService.getOrderInfo(order_number, order_date, customer_id);
            if (orderInfoResponse.getOrder_lines().size() > 0) {

                return ResponseEntity.status(HttpStatus.OK).body(orderInfoResponse);
            } else {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
