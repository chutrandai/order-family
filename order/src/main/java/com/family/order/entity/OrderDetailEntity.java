package com.family.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "order_detail")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDetailEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "orderDetailId")
    private String orderDetailId;

    @Column(name = "orderId")
    private String orderId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "unitPrice")
    private Double unitPrice;

    @Column(name = "unitCode")
    private String unitCode;

    @Column(name = "totalAmount")
    private BigDecimal totalAmount;
}
