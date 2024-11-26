package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.Exception.CustomNotFoundException;
import com.springeasystock.easystock.dto.WaveDTO;
import com.springeasystock.easystock.mapper.WaveMapper;
import com.springeasystock.easystock.model.Wave;
import com.springeasystock.easystock.repo.WaveRepository;
import com.springeasystock.easystock.service.WaveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class WaveServiceImpl implements WaveService {
    private WaveRepository waveRepository;

    @Override
    public WaveDTO createWave(WaveDTO waveDTO) {
        Wave wave = WaveMapper.toEntity(waveDTO);
        Wave savedWave = waveRepository.save(wave);
        return WaveMapper.toDTO(savedWave);
    }

    @Override
    public List<WaveDTO> getAllWaves() {
        List<Wave> waves = waveRepository.findAll();
        return waves.stream().map((wave) -> WaveMapper.toDTO(wave))
                .collect(Collectors.toList());
    }
    @Override
    public WaveDTO getWaveById(Integer waveId) {
        Wave wave = waveRepository.findById(waveId)
                .orElseThrow(() -> new CustomNotFoundException(waveId));
        return WaveMapper.toDTO(wave);

    }

    @Override
    public WaveDTO updateWave(Integer waveId, WaveDTO updatedWave) {
        Wave wave = waveRepository.findById(waveId)
                .orElseThrow(() -> new CustomNotFoundException(waveId));
        wave.setOrderList(updatedWave.getOrderList());
        wave.setWavePriority(updatedWave.getWavePriority());
        wave.setWaveStatus(updatedWave.getWaveStatus());
        Wave updatedObj =  waveRepository.save(wave);
        return WaveMapper.toDTO(updatedObj);
    }

    @Override
    public void deleteWave(Integer waveId) {
        Wave wave = waveRepository.findById(waveId)
                .orElseThrow(() -> new CustomNotFoundException(waveId));
        waveRepository.deleteById(wave.getId());
    }
}
