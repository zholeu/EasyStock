package com.springeasystock.easystock.mapper;

import com.springeasystock.easystock.dto.WaveDTO;
import com.springeasystock.easystock.dto.ZoneDTO;
import com.springeasystock.easystock.model.Wave;
import com.springeasystock.easystock.model.Zone;

public class ZoneMapper {

    public static ZoneDTO toDTO(Zone zone) {
        return  new ZoneDTO(
                zone.getId(),
                zone.getWaves(),
                zone.getName(),
                zone.getType(),
                zone.getItems(),
                zone.getItemCount()
        );
    }

    public static Zone toEntity(ZoneDTO dto) {
        return  new Zone(
                dto.getId(),
                dto.getWaves(),
                dto.getName(),
                dto.getType(),
                dto.getItems(),
                dto.getItemCount()
        );
    }


}

