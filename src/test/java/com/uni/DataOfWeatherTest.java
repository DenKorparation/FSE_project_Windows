package com.uni;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataOfWeatherTest {
    DataOfWeather dataOfWeather;

    @BeforeEach
    void setUp() {
        dataOfWeather = new DataOfWeather();
        dataOfWeather.setTime(1635840000);
        dataOfWeather.setHumidity(75);
        dataOfWeather.setPressure(1010);
        dataOfWeather.setTemp((float)5.5);
        dataOfWeather.setWindSpeed((float)1.2);
        dataOfWeather.setFeelsLikeTemp((float)5.5);
    }

    @Test
    void getTemp() {
        assertEquals(5.5, dataOfWeather.getTemp(), 0.0001);
    }

    @Test
    void getFeelsLikeTemp() {
        assertEquals(5.5, dataOfWeather.getFeelsLikeTemp(), 0.0001);
    }

    @Test
    void getWindSpeed() {
        assertEquals(1.2, dataOfWeather.getWindSpeed(), 0.0001);
    }

    @Test
    void getPressure() {
        assertEquals(1010, dataOfWeather.getPressure());
    }

    @Test
    void getHumidity() {
        assertEquals(75, dataOfWeather.getHumidity());
    }

    @Test
    void getTime() {
        assertEquals(1635840000, dataOfWeather.getTime());
    }
}