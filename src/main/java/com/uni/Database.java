package com.uni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Database {
    public void request(String nameOfCity)
    {
        System.out.println(getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + nameOfCity + "&appid=c76548e17d6b42b99e631401cd0e0f75"));
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
}
