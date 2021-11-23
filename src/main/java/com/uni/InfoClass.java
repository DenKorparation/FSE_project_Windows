package com.uni;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import static com.uni.AppController.database;

public class InfoClass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label hHumidity;

    @FXML
    private Label hpressure;

    @FXML
    private Label hwindspeed;

    private int Index;


    @FXML
    void initialize() {
        hHumidity.setText("Влажность  " + String.valueOf(database.getHourlyForecast()[Index].getHumidity()) + "%");
        hpressure.setText("Давление " + String.valueOf(database.getHourlyForecast()[Index].getPressure() + " gPa"));
        hwindspeed.setText("Скорость ветра " + String.valueOf(database.getHourlyForecast()[Index].getWindSpeed() + " м/с"));
    }

}