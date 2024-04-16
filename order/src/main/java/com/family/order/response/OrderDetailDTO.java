package com.family.order.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {
    private String productName;
    private BigDecimal quantity;
    private Double unitPrice;
    private String unitCode;
}
