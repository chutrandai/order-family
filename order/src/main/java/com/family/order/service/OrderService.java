package com.family.order.service;

import com.family.order.entity.OrderDetailEntity;
import com.family.order.entity.OrderEntity;
import com.family.order.repository.OrderDetailRepository;
import com.family.order.repository.OrderRepository;
import com.family.order.request.CreateOrUpdateOrderRequest;
import com.family.order.request.OrdersRequest;
import com.family.order.response.OrderDTO;
import com.family.order.response.OrderDetailDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class OrderService {
    private ObjectMapper mapper;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    public ResponseEntity<List<OrderDTO>> getOrders(OrdersRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize());
        List<OrderEntity> orderEntities = orderRepository.getOrders(request.getName(), request.getType(), request.getUserName(),
                request.getFromDate(), request.getToDate(), pageable);
        if (orderEntities.isEmpty()) {
            log.info("Empty list");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.info("List size = {}", orderEntities.size());
        return new ResponseEntity<>(mapper.convertValue(orderEntities, new TypeReference<>() {}), HttpStatus.OK);
    }
    public ResponseEntity<OrderDTO> getOrderDetail(String orderId) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            log.error("order id not found. orderId={}", orderId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mapper.convertValue(order.get(), OrderDTO.class), HttpStatus.NOT_FOUND);
    }
    private boolean processOrder(CreateOrUpdateOrderRequest request, OrderEntity entity) {
        // save order
        entity.setOrderName(request.getOrderName());
        entity.setUserName(request.getUserName());
        entity.setType(request.getType());
        entity.setCreatedDate(new Date());
        entity.setTotalAmount(request.getTotalAmount());
        orderRepository.save(entity);
        // save order detail
        if (entity.getOrderId() != null) {
            List<OrderDetailEntity> lstDetailEntity = new ArrayList<>();
            for (OrderDetailDTO orderDetail : request.getOrderDetails()) {
                OrderDetailEntity detailEntity = new OrderDetailEntity();
                detailEntity.setOrderId(entity.getOrderId());
                detailEntity.setProductName(orderDetail.getProductName());
                detailEntity.setQuantity(orderDetail.getQuantity());
                detailEntity.setUnitPrice(orderDetail.getUnitPrice());
                detailEntity.setUnitCode(orderDetail.getUnitCode());
                detailEntity.setTotalAmount(BigDecimal.valueOf(orderDetail.getUnitPrice() * orderDetail.getQuantity().doubleValue()));
                lstDetailEntity.add(detailEntity);
            }
            orderDetailRepository.saveAll(lstDetailEntity);
            return true;
        } else {
            log.error("create order error.");
            return false;
        }
    }
    @Transactional
    public ResponseEntity<String> createOrder(CreateOrUpdateOrderRequest request) {
        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);
        if (processOrder(request, new OrderEntity())) {
            return response;
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }
    @Transactional
    public ResponseEntity<String> updateOrder(CreateOrUpdateOrderRequest request) {
        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<OrderEntity> order = orderRepository.findById(request.getOrderId());
        if (order.isEmpty()) {
            log.error("order id not found. orderId={}", request.getOrderId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // remove order detail
        orderDetailRepository.removeAllByOrderId(request.getOrderId());
        // update
        if (processOrder(request, order.get())) {
            return response;
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }
}
