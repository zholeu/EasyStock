package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.Exception.CustomNotFoundException;
import com.springeasystock.easystock.dto.ZoneDTO;
import com.springeasystock.easystock.mapper.ZoneMapper;
import com.springeasystock.easystock.model.Zone;
import com.springeasystock.easystock.repo.ZoneRepository;
import com.springeasystock.easystock.service.ZoneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ZoneServiceImpl implements ZoneService {
    private ZoneRepository zoneRepository;

    @Override
    public ZoneDTO createZones(ZoneDTO zoneDTO) {
        Zone zone = ZoneMapper.toEntity(zoneDTO);
        Zone savedWave = zoneRepository.save(zone);
        return ZoneMapper.toDTO(savedWave);
    }

    @Override
    public List<ZoneDTO> getAllZones() {
        List<Zone> zones = zoneRepository.findAll();
        return zones.stream().map((zone) -> ZoneMapper.toDTO(zone))
                .collect(Collectors.toList());
    }

    @Override
    public ZoneDTO getZoneById(Integer zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new CustomNotFoundException(zoneId));
        return ZoneMapper.toDTO(zone);

    }

    @Override
    public ZoneDTO updateZone(Integer zoneId, ZoneDTO updatedZone) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new CustomNotFoundException(zoneId));
        zone.setWaves(updatedZone.getWaves());
        zone.setName(updatedZone.getName());
        zone.setType(updatedZone.getType());
        zone.setItems(updatedZone.getItems());
        zone.setItemCount(updatedZone.getItemCount());
        Zone updatedObj =  zoneRepository.save(zone);
        return ZoneMapper.toDTO(updatedObj);
    }

    @Override
    public void deleteZone(Integer zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new CustomNotFoundException(zoneId));
        zoneRepository.deleteById(zone.getId());
    }

}
