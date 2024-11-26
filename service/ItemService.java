package com.springeasystock.easystock.service;

import com.springeasystock.easystock.dto.EmployeeDTO;
import com.springeasystock.easystock.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO createItem(ItemDTO itemDTO);
    List<ItemDTO> getAllItems();

    ItemDTO getItemById(Integer itemId);

    ItemDTO updateItem(Integer itemId, ItemDTO updatedItem);

    void deleteItem(Integer itemId);
}
