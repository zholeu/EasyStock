package com.springeasystock.easystock.service;

import com.springeasystock.easystock.dto.OrderListDTO;
import com.springeasystock.easystock.dto.WaveDTO;

import java.util.List;

public interface WaveService {

    WaveDTO createWave(WaveDTO orderListDTO);
    List<WaveDTO> getAllWaves();

    WaveDTO getWaveById(Integer waveId);

    WaveDTO updateWave(Integer waveId, WaveDTO updatedWave);

    void deleteWave(Integer waveId);
}
