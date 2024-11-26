package com.springeasystock.easystock.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.springeasystock.easystock.dto.OrderListDTO;
import com.springeasystock.easystock.model.OrderList;
@Mapper
public interface OrderListMapperInterface {
    OrderListMapper INSTANCE = Mappers.getMapper(OrderListMapper.class);

//    @Mapping(source = "customer.id", target = "customerId")
    OrderListDTO toDTO(OrderList orderList);

    OrderList toEntity(OrderListDTO orderListDTO);
}
