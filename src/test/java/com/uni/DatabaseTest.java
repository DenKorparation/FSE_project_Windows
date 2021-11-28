package com.uni;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    Database database = new Database();

    @BeforeEach
    public void setUp() {
        database.setNameOfCity("Minsk");
        database.request();
    }

    @Test
    public void getCurWeatherData() {
        assertNotNull(database.getCurWeatherData());
    }

    @Test
    public void getHourlyForecast() {
        assertNotNull(database.getHourlyForecast());
    }

    @Test
    public void getDailyForecast() {
        assertNotNull(database.getDailyForecast());
    }

    @Test
    void request() throws Exception{
        assertTimeout(ofSeconds(10000), () -> {
            database.request();
        });
    }

    @Test
    void requestIncorrectData() throws Exception{
        assertTimeout(ofSeconds(10000), () -> {
            database.setNameOfCity("1");
            database.request();
            assertEquals(false, database.isCorrectData());
        });
    }

    @Test
    public void setNameOfCity() {
        database.setNameOfCity("Minsk");
        assertEquals("Minsk", database.getNameOfCity());
    }

    @Test
    public void getNameOfCity() {
        assertEquals("Минск", database.getNameOfCity());
    }

    @Test
    public void isCorrectData() {
        assertEquals(true, database.isCorrectData());
    }

    @Test
    public void getPartOfDay() {
        assertNotNull(database.getPartOfDay());
    }

    @Test
    public void getCur_Condition() {
        assertNotNull(database.getCur_Condition());
    }

    @Test
    public void setMapLayer() throws Exception{
        database.setMapLayer("temp_new");
    }

    @Test
    public void zoomIncrement() throws Exception{
        for(int i = 0; i < 13; i++){
            database.zoomIncrement();
        }
    }

    @Test
    public void zoomDecrement() throws Exception{
        for(int i = 0; i < 10; i++){
            database.zoomDecrement();
        }
    }

    @Test
    public void getCodeOfCountry() {
        assertEquals("BY", database.getCodeOfCountry());
    }
}