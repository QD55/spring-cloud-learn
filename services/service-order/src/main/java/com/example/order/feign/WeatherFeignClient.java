package com.example.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//  http://t.weather.sojson.com/api/weather/city/101030100
@FeignClient(value = "get-weather", url = "http://t.weather.sojson.com/api/weather")
public interface WeatherFeignClient {

    @GetMapping("/city/{cityId}")
    String getWeather(@PathVariable("cityId") String cityId);

}

