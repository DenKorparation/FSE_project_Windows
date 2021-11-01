package com.uni;

public class DataOfWeather {
    private float temp;
    private float feelsLikeTemp;
    private float windSpeed;
    private float pressure;
    private float humidity;

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTemp() {
        return temp;
    }

    public float getFeelsLikeTemp() {
        return feelsLikeTemp;
    }

    public void setFeelsLikeTemp(float feelsLikeTemp) {
        this.feelsLikeTemp = feelsLikeTemp;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
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
