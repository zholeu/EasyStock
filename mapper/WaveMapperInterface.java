package com.springeasystock.easystock.mapper;


import com.springeasystock.easystock.dto.OrderListDTO;
import com.springeasystock.easystock.model.OrderList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.springeasystock.easystock.dto.WaveDTO;
import com.springeasystock.easystock.model.Wave;
@Mapper
public interface WaveMapperInterface {
    WaveMapper INSTANCE = Mappers.getMapper(WaveMapper.class);

//    @Mapping(source = "orderList.id", target = "orderListId")
    WaveDTO toDTO(Wave wave);

    Wave toEntity(WaveDTO waveDTO);

}
