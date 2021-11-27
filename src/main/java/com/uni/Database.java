package com.uni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;

public class Database {
    private static final int numberOfHours = 48;
    private int zoom;
    private static final int numberOfDays = 7;
    private  static  final String API_KEYS = "c76548e17d6b42b99e631401cd0e0f75";
    private static final String MAP_API_KEYS = "LOSBNUlvpwa89u2MXMh5EusanAKtrRXh";
    private DataOfWeather curWeatherData = new DataOfWeather();
    private DataOfWeather[] hourlyForecast = new DataOfWeather [numberOfHours];
    private DataOfWeather[] dailyForecast = new DataOfWeather[numberOfDays];
    private String nameOfCity;
    private String codeOfCountry;
    private String cur_Condition;
    private String partOfDay;
    private boolean isCorrectData;
    private Image map;
    private Image weatherMap;
    private double cityLongitude; //долгота
    private double cityLatitude; //широта
    private String mapLayer;


    public Database(){
        zoom = 9;
        mapLayer = "pressure_new";
        isCorrectData = false;
        partOfDay = "day";
        nameOfCity = "";
        cur_Condition = "Clear";
        for(int i = 0; i < numberOfHours; i++){
            hourlyForecast[i] = new DataOfWeather();
        }
        for(int i = 0; i < numberOfDays; i++){
            dailyForecast[i] = new DataOfWeather();
        }
    }

    public void request() {

        reqCurWeather("https://api.openweathermap.org/data/2.5/weather?q=" + nameOfCity + "&lang=ru&units=metric&appid=" + API_KEYS);
        if (isCorrectData) {
            reqMap();
            reqHourlyForecast("https://pro.openweathermap.org/data/2.5/forecast/hourly?q=" + nameOfCity + "&cnt=48&lang=ru&units=metric&appid=" + API_KEYS);
            reqDailyForecast("https://api.openweathermap.org/data/2.5/forecast/daily?q=" + nameOfCity + "&cnt=7&lang=ru&units=metric&appid=" + API_KEYS);
        }
    }

    public void reqMap() {
        int xCoord, yCoord;
        xCoord = (int) ((cityLongitude + 180.d) / 360.d * Math.pow(2, zoom));
        yCoord = (int) (-(cityLatitude - 90.d) / 180.d * Math.pow(2, zoom));
        weatherMap = new Image("https://tile.openweathermap.org/map/" + mapLayer + "/" + Integer.toString(zoom) + "/" + Integer.toString(xCoord) + "/" + Integer.toString(yCoord) + ".png?appid=" + API_KEYS);
        /*map = new Image("https://www.mapquestapi.com/staticmap/v5/map?key=" + MAP_API_KEYS + "&center=" +
                (-(yCoord + 0.5) * 180.d / Math.pow(2, zoom) + 90.d) + "," + Double.toString((xCoord + 0.5) * 360.d / Math.pow(2, zoom) - 180.d) +
                "&size=256,256@2x&zoom=" + zoom);*/ //it is for correct view

        map = new Image("https://www.mapquestapi.com/staticmap/v5/map?key=" + MAP_API_KEYS + "&center=" +
                (-(yCoord + 0.5) * 180.d / Math.pow(2, zoom) + 90.d) + "," + Double.toString((xCoord + 0.5) * 360.d / Math.pow(2, zoom) - 180.d) +
                "&size=256,256@2x&zoom=" + zoom);

        System.out.println("https://tile.openweathermap.org/map/" + mapLayer + "/" + Integer.toString(zoom) + "/" + Integer.toString(xCoord) + "/" + Integer.toString(yCoord) + ".png?appid=" + API_KEYS);

        System.out.println("https://www.mapquestapi.com/staticmap/v5/map?key=" + MAP_API_KEYS + "&center=" +
                (-(yCoord + 0.5) * 180.d / Math.pow(2, zoom) + 90.d) + "," + Double.toString((xCoord + 0.5) * 360.d / Math.pow(2, zoom) - 180.d) +
                "&size=256,256@2x&zoom=" + zoom);
    }

    private void reqCurWeather(String url){
        System.out.println(url);
        try {
            isCorrectData = true;
            String output = getUrlContent(url);
            if (!output.isEmpty()) {
                JSONObject obj = new JSONObject(output);
                if (obj.getInt("cod") != 404) {
                    curWeatherData.setTemp((float)obj.getJSONObject("main").getDouble("temp"));
                    curWeatherData.setFeelsLikeTemp((float)obj.getJSONObject("main").getDouble("feels_like"));
                    curWeatherData.setWindSpeed((float)obj.getJSONObject("wind").getDouble("speed"));
                    curWeatherData.setPressure(obj.getJSONObject("main").getInt("pressure"));
                    curWeatherData.setHumidity(obj.getJSONObject("main").getInt("humidity"));
                    curWeatherData.setTime(new Date(obj.getInt("dt") * 1000L));
                    curWeatherData.setCondition(obj.getJSONArray("weather").getJSONObject(0).getString("main"));
                    curWeatherData.setDescription(obj.getJSONArray("weather").getJSONObject(0).getString("description"));
                    curWeatherData.setIdIcon(obj.getJSONArray("weather").getJSONObject(0).getString("icon"));

                    codeOfCountry = obj.getJSONObject("sys").getString("country");
                    if(curWeatherData.getIdIcon().charAt(curWeatherData.getIdIcon().length() - 1) == 'd')
                        partOfDay = "day";
                    else
                        partOfDay = "night";

                    if(String.valueOf(obj.getJSONArray("weather").getJSONObject(0).getInt("id")).charAt(0) == '7')
                        cur_Condition = "Fog";
                    else
                        cur_Condition = curWeatherData.getCondition();

                    nameOfCity = obj.getString("name");
                    cityLatitude = obj.getJSONObject("coord").getDouble("lat");
                    cityLongitude = obj.getJSONObject("coord").getDouble("lon");
                }
                else
                    isCorrectData = false;
            }
            else
                isCorrectData = false;
        } catch (Exception e){
            isCorrectData = false;
        }
    }

    private void reqHourlyForecast(String url){
        System.out.println(url);
        try{
            String output = getUrlContent(url);
            if(!output.isEmpty()){
                JSONObject obj = new JSONObject(output);
                if(obj.getInt("cod") != 404) {
                    JSONArray list = obj.getJSONArray("list");
                    for (int i = 0; i < numberOfHours; i++) {
                        hourlyForecast[i].setTemp((float)list.getJSONObject(i).getJSONObject("main").getDouble("temp"));
                        hourlyForecast[i].setFeelsLikeTemp((float)list.getJSONObject(i).getJSONObject("main").getDouble("feels_like"));
                        hourlyForecast[i].setWindSpeed((float)list.getJSONObject(i).getJSONObject("wind").getDouble("speed"));
                        hourlyForecast[i].setPressure(list.getJSONObject(i).getJSONObject("main").getInt("pressure"));
                        hourlyForecast[i].setHumidity(list.getJSONObject(i).getJSONObject("main").getInt("humidity"));

                        //set time as Date
                        hourlyForecast[i].setTime(new Date((long)list.getJSONObject(i).getInt("dt") * 1000L));
                        hourlyForecast[i].setCondition(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"));
                        hourlyForecast[i].setDescription(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"));
                        hourlyForecast[i].setIdIcon(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"));
                    }
                }else
                    isCorrectData = false;
            }
            else
                isCorrectData = false;
        } catch (Exception e){
            isCorrectData = false;
        }
    }

    private void reqDailyForecast(String url){
        System.out.println(url);
        try{
            String output = getUrlContent(url);
            if(!output.isEmpty()){
                JSONObject obj = new JSONObject(output);
                if(obj.getInt("cod") != 404) {
                    JSONArray list = obj.getJSONArray("list");
                    for (int i = 0; i < numberOfDays; i++) {
                        dailyForecast[i].setTempDay((float)list.getJSONObject(i).getJSONObject("temp").getDouble("day"));
                        dailyForecast[i].setTempNight((float)list.getJSONObject(i).getJSONObject("temp").getDouble("night"));
                        dailyForecast[i].setFeelsLikeTempNight((float)list.getJSONObject(i).getJSONObject("feels_like").getDouble("night"));
                        dailyForecast[i].setFeelsLikeTempDay((float)list.getJSONObject(i).getJSONObject("feels_like").getDouble("day"));
                        dailyForecast[i].setWindSpeed((float)list.getJSONObject(i).getDouble("speed"));
                        dailyForecast[i].setPressure(list.getJSONObject(i).getInt("pressure"));
                        dailyForecast[i].setHumidity(list.getJSONObject(i).getInt("humidity"));
                        //set time as Date
                        dailyForecast[i].setTime(new Date((long)list.getJSONObject(i).getInt("dt") * 1000L));
                        dailyForecast[i].setCondition(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"));
                        dailyForecast[i].setDescription(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"));
                        dailyForecast[i].setIdIcon(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"));
                    }
                }else
                    isCorrectData = false;
            }
            else
                isCorrectData = false;
        } catch (Exception e){
            isCorrectData = false;
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

    public String getNameOfCity(){
        return nameOfCity;
    }

    public boolean isCorrectData() {
        return isCorrectData;
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    public String getCur_Condition() {
        return cur_Condition;
    }

    public Image getMap() {
        return map;
    }

    public double getCityLongitude() {
        return cityLongitude;
    }

    public double getCityLatitude() {
        return cityLatitude;
    }

    public Image getWeatherMap() {
        return weatherMap;
    }

    public void setMapLayer(String mapLayer) {
        this.mapLayer = mapLayer;
    }

    public void zoomIncrement(){
        if(zoom < 20)
            zoom++;
    };

    public void zoomDecrement(){
        if(zoom > 0)
            zoom--;
    };

    public String getCodeOfCountry() {
        return codeOfCountry;
    }

}
