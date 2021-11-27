package com.uni;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import static com.uni.AppController.database;

/**
 * InfoClass class that loads scene with more information
 * @author Sasha
 * @version 1.1
 */

public class InfoClass {

    /**
     * hHumidity Label which gets value of hourly humidity from database
     */
    @FXML
    private Label hHumidity;
    /**
     * hpressure Label which gets value of hourly pressure from database
     */
    @FXML
    private Label hpressure;
    /**
     * hwindspeed Label which gets value of hourly wind speed from database
     */
    @FXML
    private Label hwindspeed;

    /**
     * method which gets all the information from databes
     * @param Index the number of an hour
     */
    public void updateHourly(int Index){
        hHumidity.setText("Влажность  " + String.valueOf(database.getHourlyForecast()[Index].getHumidity()) + "%");
        hpressure.setText("Давление " + String.valueOf(database.getHourlyForecast()[Index].getPressure() + " hPa"));
        hwindspeed.setText("Скорость ветра " + String.valueOf(database.getHourlyForecast()[Index].getWindSpeed() + " м/с"));
    }

    /**
     * method that initializes elements
     */
    @FXML
    void initialize() {
    }

}
