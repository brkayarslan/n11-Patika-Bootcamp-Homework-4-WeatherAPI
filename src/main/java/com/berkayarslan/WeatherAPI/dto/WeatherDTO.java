package com.berkayarslan.WeatherAPI.dto;

public record WeatherDTO(
        String cityName,
        String country,
        Integer temperature
) {

}
