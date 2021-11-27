package com.uni;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.TimeZone;

import static com.uni.AppController.database;

/**
 * dailyItemController class that implements methods connected with interface( implements {@link Initializable} class)
 * @author Sasha
 * @version 1.1
 */

public class dailyItemController implements Initializable {
    /**
     * curCond Label which gets current Condition value from database
     */
    @FXML
    private Label curCond;
    /**
     * feelsliketemp Label which gets feels like temperature value from database
     */
    @FXML
    private Label feelsliketemp;
    /**
     * temp Label which gets temperature value from database
     */
    @FXML
    private Label temp;
    /**
     * time Label which gets time value from database
     */
    @FXML
    private Label time;
    /**
     * windspeed Label which gets wind speed value from database
     */
    @FXML
    private Label windspeed;
    /**
     * TempIm ImageView for temperature image output
     */
    @FXML
    private ImageView TempIm;
    /**
     * icon ImageView for main icon image output
     */
    @FXML
    private ImageView icon;
    /**
     * wind_icon ImageView for wind icon image output
     */
    @FXML
    private ImageView wind_icon;

    /**
     * method that updates all the information about daily forecast from database
     * @param Index the number of a day
     */

    public void updateDaily(int Index){
        TempIm.getImage();
        TempIm.setImage(new Image("temp.png"));
        temp.setText(String.valueOf(database.getDailyForecast()[Index].getTempDay()) + " °/ " + (database.getDailyForecast()[Index].getTempNight() + "°"));
        System.out.println(database.getDailyForecast()[Index].getIdIcon() + ".png");
        icon.setImage(new Image(database.getDailyForecast()[Index].getIdIcon() + ".png"));

        feelsliketemp.setText("Ощущается как " + (String.valueOf(database.getDailyForecast()[Index].getFeelsLikeTempDay())) + "°/ " + (database.getDailyForecast()[Index].getFeelsLikeTempNight()) + "°");
        feelsliketemp.setLineSpacing(0.0);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd.MM");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        time.setText((sdf.format(database.getDailyForecast()[Index].getTime())));
        curCond.setText(database.getDailyForecast()[Index].getDescription());
        windspeed.setText(String.valueOf(database.getDailyForecast()[Index].getWindSpeed()) + " м/с");
        wind_icon.setImage(new Image("wind_icon.png"));
    }

    /**
     * method that initializes elements of daily forecast
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        temp.setText(Double.valueOf(0.0) + "°");
        /* wind.setText(String.valueOf(0.0));*/
        icon.setImage(new Image("01d.png"));
    }
}
