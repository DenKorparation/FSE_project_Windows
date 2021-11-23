package com.uni;

import java.io.FileNotFoundException;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AppController {

    private Database database = new Database();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button EnterButton;

    @FXML
    private TextField EnterCity;

    @FXML
    private Label Temp;

    @FXML
    private Label feelslikeTemp;

    @FXML
    private Label humidity;

    @FXML
    private Label lbl;

    @FXML
    private Label curTime;

    @FXML
    private Label pressure;

    @FXML
    private Label PartOfDay;

    @FXML
    private ImageView mainIm;

    @FXML
    private Label condition;

    @FXML
    private Label windSpeed;

    @FXML
    private String curCond;

    @FXML
    private String partOfDay;

    @FXML
    private Label result_info;

    @FXML
    private ImageView humidityIm;

    @FXML
    private ImageView windIm;

    @FXML
    private Label hourlyForecast;

    @FXML
    private ImageView map;
    @FXML
    private ImageView weatherMap;
    @FXML
    private ImageView pressureIm;
    @FXML
    private ImageView mainIcon;
    @FXML
    private ImageView magnifier;

    @FXML
    private ScrollPane hForecast;

    @FXML
    private ScrollBar hForecastScroll;

    @FXML
    void OnClickMethod() {
        database.setNameOfCity(EnterCity.getText());
        result_info.setText("Ожидайте...");
        Thread t = new Thread(() -> {
            database.request();

            Platform.runLater(() -> {
                if(database.isCorrectData()){
                    lbl.setText("Погода в " + EnterCity.getText());
                    String temp = (database.getCurWeatherData().getTemp()) +  "°";
                    Temp.setText(temp);
                    feelslikeTemp.setText("Ощущается как " + (database.getCurWeatherData().getFeelsLikeTemp())  +  "°");
                    windSpeed.setText((database.getCurWeatherData().getWindSpeed()) + " m/s");
                    humidity.setText( (database.getCurWeatherData().getHumidity()) + "%");
                    pressure.setText((database.getCurWeatherData().getPressure()) + " gPa");

                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM HH:mm z"); // какой формат нужен, выбераем
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT+3")); // если нужно даем таймзон
                    curTime.setText("Состояние на  " + (sdf.format(database.getCurWeatherData().getTime())));
                    humidityIm.getImage();
                    humidityIm.setImage(new Image("humidityIm.png"));
                    windIm.getImage();
                    windIm.setImage(new Image("wind.png"));
                    pressureIm.getImage();
                    pressureIm.setImage(new Image("pressure.png"));
                    magnifier.setImage(new Image("magnifier.png"));
                    mainIm.getImage();
                    mainIcon.getImage();
                    curCond = database.getCur_Condition();
                    partOfDay = database.getPartOfDay();
                    condition.setText(database.getCur_Condition());

                    if ((Objects.equals(curCond, "Clear"))  & (Objects.equals(partOfDay, "night"))) {
                        mainIm.setImage(new Image("night.jpg"));
                        mainIcon.setImage(new Image("01n.png"));
                    }
                    if ((Objects.equals(curCond, "Clouds")) & (Objects.equals(partOfDay, "night"))) {
                        mainIm.setImage(new Image("night.jpg"));
                        mainIcon.setImage(new Image("04n.png"));
                    }

                    if ((Objects.equals(curCond, "Clear")) & (Objects.equals(partOfDay, "day"))) {
                        mainIm.setImage(new Image("clear.jpg"));
                        mainIcon.setImage(new Image("01d.png"));
                    }
                    if ((Objects.equals(curCond, "Fog")) & (Objects.equals(partOfDay, "day"))) {
                        mainIm.setImage(new Image("fog.jpg"));
                        mainIcon.setImage(new Image("50d.png"));
                    }
                    if ((Objects.equals(curCond, "Fog")) & (Objects.equals(partOfDay, "night"))) {
                        mainIm.setImage(new Image("fog.jpg"));
                        mainIcon.setImage(new Image("50n.png"));
                    }
                    if (Objects.equals(curCond, "Clouds") & (Objects.equals(partOfDay, "day"))) {
                        mainIm.setImage(new Image("clouds.jpg"));
                        mainIcon.setImage(new Image("02d.png"));
                    }
                    if (Objects.equals(curCond, "Rain") || (Objects.equals(curCond, "Drizzle"))) {
                        mainIm.setImage(new Image("rain.jpg"));
                        mainIcon.setImage(new Image("09d.png"));
                    }
                    if (Objects.equals(curCond, "Snow")) {
                        mainIm.setImage(new Image("snow.jpg"));
                        mainIcon.setImage(new Image("13d.png"));
                    }
                    if (Objects.equals(curCond, "Thunderstorm")) {
                        mainIm.setImage(new Image("thunder.jpg"));
                        mainIcon.setImage(new Image("11d.png"));
                    }
                    hForecastScroll.valueProperty().addListener(event -> {
                        for (int i=0; i<48; i++) {
                            float hCast;
                            hCast = database.getHourlyForecast()[i].getTemp();


                        }
                    });

                    weatherMap.setImage(database.getWeatherMap());
                    map.setImage(database.getMap());

                    result_info.setText("");
                }
                else{
                    result_info.setText("Такого города не сущетсвует");
                }

            });
        });
        t.start();

    }

    @FXML
    void initialize() {

    }
}