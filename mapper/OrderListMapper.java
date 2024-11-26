package com.springeasystock.easystock.mapper;

import com.springeasystock.easystock.dto.EmployeeDTO;
import com.springeasystock.easystock.dto.OrderListDTO;
import com.springeasystock.easystock.model.Employee;
import com.springeasystock.easystock.model.OrderList;
import com.springeasystock.easystock.model.Item;

import java.util.stream.Collectors;

public class OrderListMapper {

    public static OrderListDTO toDTO(OrderList orderList) {
        return  new OrderListDTO(
                orderList.getId(),
                orderList.getCustomerId(),
                orderList.getOrderStatus(),
                orderList.getTotalPrice(),
                orderList.getOrderDate(),
                orderList.getDeliveryDate(),
                orderList.getItemIds()
        );
    }

    public static OrderList toEntity(OrderListDTO dto) {
        return  new OrderList(
                dto.getId(),
                dto.getCustomerId(),
                dto.getOrderStatus(),
                dto.getTotalPrice(),
                dto.getOrderDate(),
                dto.getDeliveryDate(),
                dto.getItemIds()
        );
    }


}

