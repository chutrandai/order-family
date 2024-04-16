package com.family.order.repository;

import com.family.order.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, String> {
    boolean removeAllByOrderId(String orderId);
}
