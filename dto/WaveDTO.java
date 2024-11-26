package com.springeasystock.easystock.dto;

import com.springeasystock.easystock.model.OrderList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WaveDTO {
    private Integer id;
    private Set<OrderList> orderList;
    private String wavePriority;
    private String waveStatus;
//    private Set<Integer> zoneIds; // List of related Zone IDs
}

