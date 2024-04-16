package com.family.order.request;

import com.family.order.response.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class CreateOrUpdateOrderRequest {
    private String orderId;
    private String orderName;
    private String type;
    private Date createdDate;
    private BigDecimal totalAmount;
    private List<OrderDetailDTO> orderDetails;
}
