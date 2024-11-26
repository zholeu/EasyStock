package com.springeasystock.easystock.controller;


import com.springeasystock.easystock.dto.OrderListDTO;
import com.springeasystock.easystock.dto.RoleDTO;
import com.springeasystock.easystock.dto.WaveDTO;
import com.springeasystock.easystock.service.OrderListService;
import com.springeasystock.easystock.service.WaveService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/waves")
public class WaveController {

    private WaveService waveService;


    @PostMapping
    public ResponseEntity<WaveDTO> createWaves(@RequestBody WaveDTO waveDTO){
        WaveDTO savedOrderList = waveService.createWave(waveDTO);
        return new ResponseEntity<>(savedOrderList, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<WaveDTO>> getAllWaves(){
        List<WaveDTO> waves = waveService.getAllWaves();
        return ResponseEntity.ok(waves);
    }
    @GetMapping("{id}")
    public ResponseEntity<WaveDTO> getWaveById(@PathVariable("id") Integer waveId){
        WaveDTO waveDTO = waveService.getWaveById(waveId);
        return ResponseEntity.ok(waveDTO);

    }

    @PutMapping("{id}")
    public ResponseEntity<WaveDTO> updateWave(@PathVariable("id")Integer waveId,
                                              @RequestBody WaveDTO updatedWave){
        WaveDTO waveDTO = waveService.updateWave(waveId, updatedWave);
        return ResponseEntity.ok(waveDTO);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWave(@PathVariable("id")Integer waveId){
        waveService.deleteWave(waveId);
        return ResponseEntity.ok("Deleted Successfully!");

    }
}
