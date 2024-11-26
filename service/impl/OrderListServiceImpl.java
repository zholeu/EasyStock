package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.Exception.CustomNotFoundException;
import com.springeasystock.easystock.dto.OrderListDTO;
import com.springeasystock.easystock.mapper.OrderListMapper;
import com.springeasystock.easystock.model.Item;
import com.springeasystock.easystock.model.OrderList;
import com.springeasystock.easystock.repo.ItemRepository;
import com.springeasystock.easystock.repo.OrderListRepository;
import com.springeasystock.easystock.service.OrderListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderListServiceImpl implements OrderListService {
    private OrderListRepository orderListRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public OrderListDTO createOrderList(OrderListDTO orderListDTO) {
        OrderList orderList = OrderListMapper.toEntity(orderListDTO);
        OrderList savedOrderList = orderListRepository.save(orderList);
        return OrderListMapper.toDTO(savedOrderList);
    }

    @Override
    public List<OrderListDTO> getAllOrderLists() {
        List<OrderList> orderLists = orderListRepository.findAll();
        return orderLists.stream().map((orderList) -> OrderListMapper.toDTO(orderList))
                .collect(Collectors.toList());
    }

    @Override
    public OrderList assignItemToOrderList(Integer orderListId, Integer itemId) {
        Set<Item> itemSet = null;
        OrderList orderList = orderListRepository.findById(orderListId).get();
        Item item = itemRepository.findById(itemId).get();
        itemSet = orderList.getItemIds();
        itemSet.add(item);
        orderList.setItemIds(itemSet);
        return orderListRepository.save(orderList);
    }

    @Override
    public OrderListDTO getOrderListById(Integer orderListId) {
        OrderList orderList = orderListRepository.findById(orderListId)
                .orElseThrow(() -> new CustomNotFoundException(orderListId));
        return OrderListMapper.toDTO(orderList);

    }

    @Override
    public OrderListDTO updateOrderList(Integer orderListId, OrderListDTO updatedItem) {
        OrderList orderList = orderListRepository.findById(orderListId)
                .orElseThrow(() -> new CustomNotFoundException(orderListId));
        orderList.setCustomerId(updatedItem.getCustomerId());
        orderList.setOrderStatus(updatedItem.getOrderStatus());
        orderList.setTotalPrice(updatedItem.getTotalPrice());
        orderList.setOrderDate(updatedItem.getOrderDate());
        orderList.setDeliveryDate(updatedItem.getDeliveryDate());
        OrderList updatedObj =  orderListRepository.save(orderList);
        return OrderListMapper.toDTO(updatedObj);
    }

    @Override
    public void deleteOrderList(Integer orderListId) {
        OrderList orderList = orderListRepository.findById(orderListId)
                .orElseThrow(() -> new CustomNotFoundException(orderListId));
        orderListRepository.deleteById(orderList.getId());
    }

}
