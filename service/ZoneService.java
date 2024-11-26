package com.springeasystock.easystock.service;

import com.springeasystock.easystock.dto.WaveDTO;
import com.springeasystock.easystock.dto.ZoneDTO;

import java.util.List;

public interface ZoneService {
    ZoneDTO createZones(ZoneDTO zoneDTO);
    List<ZoneDTO> getAllZones();

    ZoneDTO getZoneById(Integer zoneId);

    ZoneDTO updateZone(Integer zoneId, ZoneDTO updatedZone);

    void deleteZone(Integer zoneId);
}
