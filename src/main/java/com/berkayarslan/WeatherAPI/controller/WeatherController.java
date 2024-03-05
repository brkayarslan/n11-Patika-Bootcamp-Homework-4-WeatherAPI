package com.berkayarslan.WeatherAPI.controller;

import com.berkayarslan.WeatherAPI.controller.validation.CityNameConstraint;
import com.berkayarslan.WeatherAPI.dto.WeatherDTO;
import com.berkayarslan.WeatherAPI.service.WeatherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/weather")
@Validated
public class WeatherController {

     private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public ResponseEntity<WeatherDTO> getWeather(@PathVariable("city") @Valid @CityNameConstraint @NotBlank String city){
        return ResponseEntity.ok(weatherService.getWeatherByCityName(city));
    }


}
