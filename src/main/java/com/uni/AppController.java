package com.uni;

import java.io.FileNotFoundException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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
        private ImageView sky;


        @FXML
        private Label windSpeed;

        @FXML
        void OnClickMethod(ActionEvent event) {

        }

    @FXML
    void initialize() {
        EnterButton.setOnAction(event -> {
            EnterButton.setText("Clicked");
            lbl.setText("Город " + EnterCity.getText());
            database.setNameOfCity(EnterCity.getText());
            database.request();
            String temp = (database.getCurWeatherData().getTemp()) +  "°";
            Temp.setText(temp);
            feelslikeTemp.setText("Ощущается как " + (database.getCurWeatherData().getFeelsLikeTemp())  +  "°");
            windSpeed.setText("Скорость ветра: " +(database.getCurWeatherData().getWindSpeed()) + " m/s");
            humidity.setText("Влажность: " + (database.getCurWeatherData().getHumidity()) + "%");
            pressure.setText("Давление: " + (database.getCurWeatherData().getPressure()) + " gPa");
            curTime.setText("Сейчас " + (database.getCurWeatherData().getTime()));

            PartOfDay.setText(database.getPartOfDay());



        });
    }




}