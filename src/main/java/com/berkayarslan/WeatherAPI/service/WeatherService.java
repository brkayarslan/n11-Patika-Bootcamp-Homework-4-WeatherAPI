package com.berkayarslan.WeatherAPI.service;

import com.berkayarslan.WeatherAPI.constans.Constants;
import com.berkayarslan.WeatherAPI.dto.WeatherDTO;
import com.berkayarslan.WeatherAPI.dto.WeatherResponse;
import com.berkayarslan.WeatherAPI.mapper.WeatherMapper;
import com.berkayarslan.WeatherAPI.model.Weather;
import com.berkayarslan.WeatherAPI.repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper =new ObjectMapper();

    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
    }

    public WeatherDTO getWeatherByCityName(String city){
        Optional<Weather> weather = weatherRepository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(city);

        if(weather.isEmpty()){
            Weather weatherFromWeatherStack = getWeatherFromWeatherStack(city);
            return WeatherMapper.INSTANCE.weatherConvertToDTO(weatherFromWeatherStack) ;
        }

        if(weather.get().getUpdatedTime().isBefore(LocalDateTime.now().minusMinutes(30))){
            Weather weatherFromWeatherStack = getWeatherFromWeatherStack(city);
            return WeatherMapper.INSTANCE.weatherConvertToDTO(weatherFromWeatherStack) ;
        }

        return WeatherMapper.INSTANCE.weatherConvertToDTO(weather.get());
    }

    private Weather getWeatherFromWeatherStack(String city){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getWeatherStackUrl(city),String.class);

        try {
            WeatherResponse weatherResponse = objectMapper.readValue(responseEntity.getBody(),WeatherResponse.class);
            return saveWeather(city,weatherResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Weather saveWeather(String city,WeatherResponse weatherResponse){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Weather weather = new Weather(city,
                              weatherResponse.location().name(),
                              weatherResponse.location().country(),
                              weatherResponse.current().temperature(),
                              LocalDateTime.now(),
                              LocalDateTime.parse(weatherResponse.location().localtime(),dateTimeFormatter));
        return weatherRepository.save(weather);
    }

    private String getWeatherStackUrl(String city){
        return Constants.API_URL + Constants.API_KEY_PARAM + Constants.API_KEY + Constants.QUERY_KEY_QUERY + city;
    }
}























