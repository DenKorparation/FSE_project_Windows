package com.uni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

public class Database {
    private DataOfWeather curWeatherData = new DataOfWeather();
    //private DataOfWeather[] hourlyForecast = new DataOfWeather[48];
    //private DataOfWeather[] dailyForecast = new DataOfWeather[7];

    public void request(String nameOfCity)
    {
        reqCurWeather("https://api.openweathermap.org/data/2.5/weather?q=" + nameOfCity + "&units=metric&appid=c76548e17d6b42b99e631401cd0e0f75");
    }
    private void reqCurWeather(String url){
        String output = getUrlContent(url);
        if(!output.isEmpty()){
            JSONObject obj = new JSONObject(output);
            curWeatherData.setTemp(obj.getJSONObject("main").getFloat("temp"));
            curWeatherData.setFeelsLikeTemp(obj.getJSONObject("main").getFloat("feels_like"));
            curWeatherData.setWindSpeed(obj.getJSONObject("wind").getFloat("speed"));
            curWeatherData.setPressure(obj.getJSONObject("main").getFloat("pressure"));
            curWeatherData.setHumidity(obj.getJSONObject("main").getFloat("humidity"));
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
            System.out.println("Такой город не был найден!");
        }
        return content.toString();
    }

    public DataOfWeather getCurWeatherData() {
        return curWeatherData;
    }
}
