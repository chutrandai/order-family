package com.family.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue
    @Column(name = "orderId")
    private String orderId;

    @Column(name = "orderName")
    private String orderName;

    @Column(name = "type")
    private String type;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "totalAmount")
    private BigDecimal totalAmount;

    @Column(name = "modifyDate")
    private Date modifyDate;
}
