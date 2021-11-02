package com.uni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class Database {
    private static final int numberOfHours = 48;
    private static final int numberOfDays = 7;
    private DataOfWeather curWeatherData = new DataOfWeather();
    private DataOfWeather[] hourlyForecast = new DataOfWeather [numberOfHours];
    private DataOfWeather[] dailyForecast = new DataOfWeather[numberOfDays];
    private String nameOfCity;

    public Database(){
        nameOfCity = "";
        for(int i = 0; i < numberOfHours; i++){
            hourlyForecast[i] = new DataOfWeather();
        }
        for(int i = 0; i < numberOfDays; i++){
            dailyForecast[i] = new DataOfWeather();
        }
    }

    public void request()
    {
        reqCurWeather("https://api.openweathermap.org/data/2.5/weather?q=" + nameOfCity + "&units=metric&appid=c76548e17d6b42b99e631401cd0e0f75");
        reqHourlyForecast("https://pro.openweathermap.org/data/2.5/forecast/hourly?q=" + nameOfCity + "&cnt=48&units=metric&appid=c76548e17d6b42b99e631401cd0e0f75");
    }
    private void reqCurWeather(String url){
        String output = getUrlContent(url);
        if(!output.isEmpty()){
            JSONObject obj = new JSONObject(output);
            if(obj.getInt("cod") != 404) {
                curWeatherData.setTemp(obj.getJSONObject("main").getFloat("temp"));
                curWeatherData.setFeelsLikeTemp(obj.getJSONObject("main").getFloat("feels_like"));
                curWeatherData.setWindSpeed(obj.getJSONObject("wind").getFloat("speed"));
                curWeatherData.setPressure(obj.getJSONObject("main").getInt("pressure"));
                curWeatherData.setHumidity(obj.getJSONObject("main").getInt("humidity"));
                curWeatherData.setTime(obj.getInt("dt"));
            } else{
                System.out.println(obj.getString("message"));
            }
        }
    }

    private void reqHourlyForecast(String url){
        String output = getUrlContent(url);
        if(!output.isEmpty()){
            JSONObject obj = new JSONObject(output);
            if(obj.getInt("cod") != 404) {
                JSONArray list = obj.getJSONArray("list");
                for (int i = 0; i < numberOfHours; i++) {
                    hourlyForecast[i].setTemp(list.getJSONObject(i).getJSONObject("main").getFloat("temp"));
                    hourlyForecast[i].setFeelsLikeTemp(list.getJSONObject(i).getJSONObject("main").getFloat("feels_like"));
                    hourlyForecast[i].setWindSpeed(list.getJSONObject(i).getJSONObject("wind").getFloat("speed"));
                    hourlyForecast[i].setPressure(list.getJSONObject(i).getJSONObject("main").getInt("pressure"));
                    hourlyForecast[i].setHumidity(list.getJSONObject(i).getJSONObject("main").getInt("humidity"));
                    hourlyForecast[i].setTime(list.getJSONObject(i).getInt("dt"));
                }
            }
            else{
                System.out.println(obj.getString("message"));
            }
        }
    }

    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();
        try{
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }catch (Exception e){
            return "{\"cod\":\"404\",\"message\":\"city not found\"}";
        }
        return content.toString();
    }

    public DataOfWeather getCurWeatherData() {
        return curWeatherData;
    }

    public DataOfWeather[] getHourlyForecast() {
        return hourlyForecast;
    }

    public DataOfWeather[] getDailyForecast() {
        return dailyForecast;
    }

    public void setNameOfCity(String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }
}
