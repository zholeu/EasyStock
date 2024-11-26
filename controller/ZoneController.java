package com.springeasystock.easystock.controller;

import com.springeasystock.easystock.dto.WaveDTO;
import com.springeasystock.easystock.dto.ZoneDTO;
import com.springeasystock.easystock.service.WaveService;
import com.springeasystock.easystock.service.ZoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private ZoneService zoneService;


    @PostMapping
    public ResponseEntity<ZoneDTO> createWaves(@RequestBody ZoneDTO zoneDTO){
        ZoneDTO savedZone = zoneService.createZones(zoneDTO);
        return new ResponseEntity<>(savedZone, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<ZoneDTO>> getAllWaves(){
        List<ZoneDTO> zones = zoneService.getAllZones();
        return ResponseEntity.ok(zones);
    }

    @GetMapping("{id}")
    public ResponseEntity<ZoneDTO> getWaveById(@PathVariable("id") Integer zoneId){
        ZoneDTO zoneDTO = zoneService.getZoneById(zoneId);
        return ResponseEntity.ok(zoneDTO);

    }

    @PutMapping("{id}")
    public ResponseEntity<ZoneDTO> updateWave(@PathVariable("id")Integer zoneId,
                                              @RequestBody ZoneDTO updatedZone){
        ZoneDTO zoneDTO = zoneService.updateZone(zoneId, updatedZone);
        return ResponseEntity.ok(zoneDTO);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWave(@PathVariable("id")Integer zoneId){
        zoneService.deleteZone(zoneId);
        return ResponseEntity.ok("Deleted Successfully!");

    }
}
