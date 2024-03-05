package com.berkayarslan.WeatherAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "WEATHER")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestedCityName;
    private String cityName;
    private String country;
    private Integer temperature;
    private LocalDateTime updatedTime;
    private LocalDateTime responseLocalTime;

    public Weather(String requestedCityName, String cityName, String country, Integer temprature, LocalDateTime updatedTime, LocalDateTime responseLocalTime) {
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temprature;
        this.updatedTime = updatedTime;
        this.responseLocalTime = responseLocalTime;
    }
}
