package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.Exception.CustomNotFoundException;
import com.springeasystock.easystock.dto.ItemDTO;
import com.springeasystock.easystock.mapper.EmployeeMapper;
import com.springeasystock.easystock.mapper.ItemMapper;
import com.springeasystock.easystock.model.Employee;
import com.springeasystock.easystock.model.Item;
import com.springeasystock.easystock.repo.ItemRepository;
import com.springeasystock.easystock.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = ItemMapper.toEntity(itemDTO);
        Item savedItem = itemRepository.save(item);
        return ItemMapper.toDTO(savedItem);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map((item) -> ItemMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemById(Integer itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new CustomNotFoundException(itemId));
        return ItemMapper.toDTO(item);

    }

    @Override
    public ItemDTO updateItem(Integer itemId, ItemDTO updatedItem) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new CustomNotFoundException(itemId));
        item.setName(updatedItem.getName());
        item.setSupplier(updatedItem.getSupplier());
        item.setSize(updatedItem.getSize());
        item.setPrice(updatedItem.getPrice());
        item.setAsile(updatedItem.getAsile());
        item.setRack(updatedItem.getRack());
        item.setShelf(updatedItem.getShelf());
        Item updatedItemObj =  itemRepository.save(item);
        return ItemMapper.toDTO(updatedItemObj);
    }

    @Override
    public void deleteItem(Integer itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new CustomNotFoundException(itemId));
        itemRepository.deleteById(item.getId());
    }

}
