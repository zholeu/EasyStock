package com.springeasystock.easystock.controller;

import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private ItemService itemService;


    @PostMapping
    public ResponseEntity<ItemDTO> createEmployee(@RequestBody ItemDTO itemDTO){
        ItemDTO savedItem = itemService.createItem(itemDTO);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllEmployee(){
        List<ItemDTO> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemDTO> getEmployeeById(@PathVariable("id") Long itemId){
        ItemDTO savedItem = itemService.getItemById(itemId);
        return ResponseEntity.ok(savedItem);

    }

    @PutMapping("{id}")
    public ResponseEntity<ItemDTO> updateEmployee(@PathVariable("id")Long itemId,
                                                      @RequestBody ItemDTO updatedItem){
        ItemDTO itemDTO = itemService.updateItem(itemId, updatedItem);
        return ResponseEntity.ok(itemDTO);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")Long itemId){
        itemService.deleteItem(itemId);
        return ResponseEntity.ok("Deleted Successfully!");

    }
}
