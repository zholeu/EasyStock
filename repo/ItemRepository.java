package com.springeasystock.easystock.repo;

import com.springeasystock.easystock.model.Customer;
import com.springeasystock.easystock.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
