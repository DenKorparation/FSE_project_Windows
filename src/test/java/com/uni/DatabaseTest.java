package com.uni;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
        database.setNameOfCity("Minsk");
    }

    @Test
    void getCurWeatherData() {
        assertNotNull(database.getCurWeatherData());
    }

    @Test
    void getHourlyForecast() {
        assertNotNull(database.getHourlyForecast());
    }

    @Test
    void getDailyForecast() {
        assertNotNull(database.getDailyForecast());
    }

    @Test
    void request() throws Exception{
        assertTimeout(ofSeconds(5000), () -> {
            database.request();
        });
    }
}