package com.springeasystock.easystock.dto;

import com.springeasystock.easystock.model.Item;
import com.springeasystock.easystock.model.Wave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZoneDTO {
    private Integer id;
    private Set<Wave> waves;
    private String name;
    private String type;
    private Set<Item> items;
    private Integer itemCount;
}

