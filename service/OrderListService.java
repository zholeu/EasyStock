package com.springeasystock.easystock.service;

import com.springeasystock.easystock.dto.EmployeeDTO;
import com.springeasystock.easystock.dto.OrderListDTO;
import com.springeasystock.easystock.model.OrderList;

import java.util.List;

public interface OrderListService {
    OrderListDTO createOrderList(OrderListDTO orderListDTO);
    List<OrderListDTO> getAllOrderLists();

    OrderList assignItemToOrderList(Integer orderListId, Integer itemId);

    OrderListDTO getOrderListById(Integer orderListId);

    OrderListDTO updateOrderList(Integer orderListId, OrderListDTO updatedItem);

    void deleteOrderList(Integer orderListId);
}
