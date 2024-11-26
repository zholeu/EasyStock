package com.springeasystock.easystock.controller;

import com.springeasystock.easystock.dto.CustomerDTO;
import com.springeasystock.easystock.dto.EmployeeDTO;
import com.springeasystock.easystock.dto.ItemDTO;
import com.springeasystock.easystock.dto.OrderListDTO;
import com.springeasystock.easystock.model.OrderList;
import com.springeasystock.easystock.service.EmployeeService;
import com.springeasystock.easystock.service.OrderListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orderlists")
public class OrderListController {

    private OrderListService orderListService;


    @PostMapping
    public ResponseEntity<OrderListDTO> createOrderList(@RequestBody OrderListDTO orderListDTO){
        OrderListDTO savedOrderList = orderListService.createOrderList(orderListDTO);
        return new ResponseEntity<>(savedOrderList, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<OrderListDTO>> getAllOrderLists(){
        List<OrderListDTO> orderLists = orderListService.getAllOrderLists();
        return ResponseEntity.ok(orderLists);
    }

    @PutMapping("/{orderListId}/item/{itemId}")
    public OrderList assignItemToOrderList(
            @PathVariable Integer orderListId,
            @PathVariable Integer itemId
    ){
        return orderListService.assignItemToOrderList(orderListId, itemId);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderListDTO> getEmployeeById(@PathVariable("id") Integer orderListId){
        OrderListDTO orderListDTO = orderListService.getOrderListById(orderListId);
        return ResponseEntity.ok(orderListDTO);

    }

    @PutMapping("{id}")
    public ResponseEntity<OrderListDTO> updateEmployee(@PathVariable("id")Integer orderListId,
                                                  @RequestBody OrderListDTO updatedItem){
        OrderListDTO orderListDTO = orderListService.updateOrderList(orderListId, updatedItem);
        return ResponseEntity.ok(orderListDTO);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")Integer orderListId){
        orderListService.deleteOrderList(orderListId);
        return ResponseEntity.ok("Deleted Successfully!");

    }


}