package com.berkayarslan.WeatherAPI.dto;

public record WeatherResponse(
        Request request,
        Location location,
        Current current
) {
}
