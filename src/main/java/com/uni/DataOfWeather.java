package com.uni;

public class DataOfWeather {
    private float temp;
    private float feels_like_temp;
    private float wind_speed;
    private float pressure;
    private float humidity;

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTemp() {
        return temp;
    }

    public float getFeels_like_temp() {
        return feels_like_temp;
    }

    public void setFeels_like_temp(float feels_like_temp) {
        this.feels_like_temp = feels_like_temp;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
