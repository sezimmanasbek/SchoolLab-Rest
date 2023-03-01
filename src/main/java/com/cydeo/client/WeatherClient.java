package com.cydeo.client;

import com.cydeo.dto.weather.WeatherDTOClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://api.weatherstack.com",name = "WEATHER-CLIENT")
public interface WeatherClient {

    @GetMapping("/current")
    WeatherDTOClient getWeatherC(@RequestParam(value = "access_key") String key,
                                 @RequestParam(value = "query") String state);
}
