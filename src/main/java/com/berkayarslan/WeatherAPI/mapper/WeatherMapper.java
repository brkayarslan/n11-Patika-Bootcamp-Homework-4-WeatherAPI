package com.berkayarslan.WeatherAPI.mapper;

import com.berkayarslan.WeatherAPI.dto.WeatherDTO;
import com.berkayarslan.WeatherAPI.model.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    WeatherDTO weatherConvertToDTO(Weather weather);

}
