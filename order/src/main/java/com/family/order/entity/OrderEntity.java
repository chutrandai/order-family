package com.family.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pay_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private BigDecimal orderId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "type")
    private String type;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "modify_date")
    private Date modifyDate;
}
