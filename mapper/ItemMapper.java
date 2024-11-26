package com.springeasystock.easystock.mapper;

import com.springeasystock.easystock.dto.EmployeeDTO;
import com.springeasystock.easystock.dto.ItemDTO;
import com.springeasystock.easystock.model.Employee;
import com.springeasystock.easystock.model.Item;

public class ItemMapper {

    public static ItemDTO toDTO(Item item) {
        return  new ItemDTO(
                item.getId(),
                item.getName(),
                item.getSupplier(),
                item.getSize(),
                item.getPrice(),
                item.getAsile(),
                item.getRack(),
                item.getShelf(),
                item.getOrderLists()
        );
    }

    public static Item toEntity(ItemDTO dto) {
        return  new Item(
                dto.getId(),
                dto.getName(),
                dto.getSupplier(),
                dto.getSize(),
                dto.getPrice(),
                dto.getAsile(),
                dto.getRack(),
                dto.getShelf(),
                dto.getOrderLists()
        );
    }


}

