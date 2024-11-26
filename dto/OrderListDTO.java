package com.springeasystock.easystock.dto;

import com.springeasystock.easystock.model.Customer;
import com.springeasystock.easystock.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDTO {
    private Integer id;
    private Customer customerId;
    private String orderStatus;
    private Double totalPrice;
//    private Timestamp orderDate  = new Timestamp(System.currentTimeMillis());;
    private Timestamp orderDate;
    private Timestamp deliveryDate;
    private Set<Item> itemIds; // List of item IDs associated with this order
}

