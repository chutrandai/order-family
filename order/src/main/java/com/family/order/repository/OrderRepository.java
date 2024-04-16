package com.family.order.repository;

import com.family.order.entity.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    @Query("select bo from OrderEntity bo where bo.orderName like concat('%', concat(:orderName, '%')) " +
            " and (:type is null or bo.type = :type) " +
            " and (:userName is null or bo.userName = :userName) " +
            " and bo.createdDate between :fromDate and :toDate ")
    List<OrderEntity> getOrders(@Param("orderName") String orderName, @Param("type") String type, @Param("userName") String userName,
                                       @Param("fromDate") Date fromDate, @Param("toDate") Date toDate, Pageable pageable);
}
