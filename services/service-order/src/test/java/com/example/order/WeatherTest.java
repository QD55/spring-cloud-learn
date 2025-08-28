package com.example.order;


import com.example.order.feign.WeatherFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherTest {

    @Autowired
    WeatherFeignClient weatherFeignClient;

    @Test
    void getWeatherTest() {

        System.out.println(weatherFeignClient.getWeather("101030200"));

    }
}
