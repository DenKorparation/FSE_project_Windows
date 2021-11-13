package com.uni;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AppController {

    private Database database = new Database();

        @FXML
        private ImageView icon;

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
        private Label pressure;

        @FXML
        private Label windSpeed;

    public AppController() throws FileNotFoundException {
    }

    @FXML
        void OnClickMethod(ActionEvent event) {

        }

    @FXML
    void initialize() {
        EnterButton.setOnAction(event -> {
            EnterButton.setText("Clicked");
            lbl.setText("Город: " + EnterCity.getText());
            database.setNameOfCity(EnterCity.getText());
            database.request();
            String temp = "Температура: " + Float.toString(database.getCurWeatherData().getTemp()) +  "°C";
            Temp.setText(temp);
            feelslikeTemp.setText("Ощущаемая температура: " + Float.toString(database.getCurWeatherData().getFeelsLikeTemp())  +  "°C");
            windSpeed.setText("Скорость ветра: " + Float.toString(database.getCurWeatherData().getWindSpeed()) + " m/s");
            humidity.setText("Влажность: " + Float.toString(database.getCurWeatherData().getHumidity()) + "%");
            pressure.setText("Давление: " + Float.toString(database.getCurWeatherData().getPressure()) + " gPa");
        });
    }




}