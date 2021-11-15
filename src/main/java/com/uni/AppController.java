package com.uni;

import java.io.FileNotFoundException;
import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Label HourlyForecast;

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
    private Label result_info;

    @FXML
    private Label hourlyForecast;

    @FXML
    private ImageView humidityIm;

    @FXML
    private ImageView windIm;

    @FXML
    void OnClickMethod(ActionEvent event) {
        /*EnterButton.setText("Clicked");*/               // ТОЧНО НУЖНО????
        database.setNameOfCity(EnterCity.getText());
        result_info.setText("Ожидайте..."); //!!!!!!!!!!!!ДОБАВЬ ТАКУЮ НАДПИСЬ!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Thread t = new Thread(() -> {
            database.request();

            Platform.runLater(() -> {
                if(database.isCorrectData()){
                    lbl.setText("Город " + EnterCity.getText());
                    String temp = (database.getCurWeatherData().getTemp()) +  "°";
                    Temp.setText(temp);
                    feelslikeTemp.setText("Ощущается как " + (database.getCurWeatherData().getFeelsLikeTemp())  +  "°");
                    windSpeed.setText((database.getCurWeatherData().getWindSpeed()) + " m/s");
                    humidity.setText( (database.getCurWeatherData().getHumidity()) + "%");
                    pressure.setText("Давление: " + (database.getCurWeatherData().getPressure()) + " gPa");
                    curTime.setText("Сейчас " + (database.getCurWeatherData().getTime()));
                    humidityIm.getImage();
                    humidityIm.setImage(new Image("humidityIm.png"));
                    windIm.getImage();
                    windIm.setImage(new Image("wind.png"));
                    mainIm.getImage();
                    curCond = database.getCur_Condition();
                    if (Objects.equals(curCond, "Clear")) {
                        mainIm.setImage(new Image("clear.jpg"));
                        condition.setText("Ясно");
                    }
                    if (Objects.equals(curCond, "Clouds")) {

                        mainIm.setImage(new Image("clouds.jpg"));
                        condition.setText("Облачно");
                    }
                /*for (int i=0; i<48; i++) {
                    float hCast = database.getHourlyForecast()[i].getTemp();
                    hourlyForecast.getText(hCast);

                }*/
                    result_info.setText("");
                }
                else{
                    //do someting
                }

            });
        });
        t.start();

    }

    @FXML
    void initialize() {

    }
}