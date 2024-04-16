package com.family.order.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class OrdersRequest extends PagingRequest {
    private String name;
    private String userName;
    private String type;
    private Date fromDate;
    private Date toDate;
}
