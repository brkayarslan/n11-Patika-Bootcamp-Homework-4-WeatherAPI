package com.berkayarslan.WeatherAPI.repository;

import com.berkayarslan.WeatherAPI.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather,Long> {

    //Select * from entity where requestedCityName order by updateTime desc limit 1
    Optional<Weather> findFirstByRequestedCityNameOrderByUpdatedTimeDesc(String city);
}
