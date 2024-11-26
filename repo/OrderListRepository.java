package com.springeasystock.easystock.repo;

import com.springeasystock.easystock.model.Customer;
import com.springeasystock.easystock.model.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderList, Integer> {
}
