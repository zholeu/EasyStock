package com.springeasystock.easystock.mapper;

import com.springeasystock.easystock.dto.OrderListDTO;
import com.springeasystock.easystock.dto.WaveDTO;
import com.springeasystock.easystock.model.OrderList;
import com.springeasystock.easystock.model.Wave;
import com.springeasystock.easystock.model.Zone;

import java.util.stream.Collectors;

public class WaveMapper {

    public static WaveDTO toDTO(Wave wave) {
        return  new WaveDTO(
                wave.getId(),
                wave.getOrderList(),
                wave.getWavePriority(),
                wave.getWaveStatus()
        );
    }


    public static Wave toEntity(WaveDTO dto) {
        return  new Wave(
                dto.getId(),
                dto.getOrderList(),
                dto.getWavePriority(),
                dto.getWaveStatus()
        );
    }
}

