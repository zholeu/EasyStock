package com.springeasystock.easystock.record;

import com.springeasystock.easystock.controller.validator.ValidCustomer;
import com.springeasystock.easystock.controller.validator.ValidItem;
import com.springeasystock.easystock.model.Customer;
import com.springeasystock.easystock.model.Item;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDTO {
    private Long id;
    @ValidCustomer
    private Customer customerId;
    @Size(min = 2, max = 50, message = "Order Status must be between 2 and 50 characters")
    private String orderStatus;
    @Positive(message = "Total price must be greater than 0")
    @Digits(integer = 6, fraction = 2, message = "Total price must have at most 6 digits and 2 decimal places")
    private Double totalPrice;
    private Timestamp orderDate;
    private Timestamp deliveryDate;
    @ValidItem
    private Set<Item> itemIds;
}

