package com.springeasystock.easystock.dto;

import com.springeasystock.easystock.model.Item;
import com.springeasystock.easystock.model.OrderList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Integer id;
    private String name;
    private String supplier;
    private float size;
    private float price;
    private int asile;
    private int rack;
    private int shelf;
    private Set<OrderList> orderLists; // List of item IDs associated with this order

}
