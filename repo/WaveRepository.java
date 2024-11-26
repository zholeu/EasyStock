package com.springeasystock.easystock.repo;

import com.springeasystock.easystock.model.Customer;
import com.springeasystock.easystock.model.Wave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaveRepository extends JpaRepository<Wave, Integer> {
}
