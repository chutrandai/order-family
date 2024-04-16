package com.family.order.controller;

import com.family.order.request.CreateOrUpdateOrderRequest;
import com.family.order.request.OrdersRequest;
import com.family.order.response.OrderDTO;
import com.family.order.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@Slf4j
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    @PostMapping("")
    public ResponseEntity<List<OrderDTO>> getOrders(@RequestBody OrdersRequest request) {
        return orderService.getOrders(request);
    }
    @GetMapping("detail")
    public ResponseEntity<OrderDTO> getOrders(@Param("orderId") String orderId) {
        return orderService.getOrderDetail(orderId);
    }
    @PostMapping("create")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrUpdateOrderRequest request) {
        return orderService.createOrder(request);
    }
    @PostMapping("update")
    public ResponseEntity<String> updateOrder(@RequestBody CreateOrUpdateOrderRequest request) {
        return orderService.updateOrder(request);
    }
}
