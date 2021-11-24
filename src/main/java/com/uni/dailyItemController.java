package com.uni;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import static com.uni.AppController.database;

public class dailyItemController implements Initializable {
    @FXML
    private Label curCond;
    @FXML
    private Label feelsliketemp;
    @FXML
    private Label temp;
    @FXML
    private Label time;
    @FXML
    private Label windspeed;
    @FXML
    private ImageView icon;

    private int Index;

    public void updateDaily(int Index){
        this.Index = Index;
        temp.setText("Температура " + String.valueOf(database.getDailyForecast()[Index].getTemp()) + "°");
        System.out.println(database.getDailyForecast()[Index].getIdIcon() + ".png");
        icon.setImage(new Image(database.getDailyForecast()[Index].getIdIcon() + ".png"));
        feelsliketemp.setText("Ощущается как " + (String.valueOf(database.getDailyForecast()[Index].getFeelsLikeTemp())) + "°" );
        SimpleDateFormat sdf = new SimpleDateFormat("E. dd.MM HH:mm z");
        /* sdf.setTimeZone(TimeZone.getTimeZone("GMT+3"));*/
        time.setText((sdf.format(database.getDailyForecast()[Index].getTime())));
        curCond.setText(database.getDailyForecast()[Index].getDescription());
        windspeed.setText("Скорость ветра " + String.valueOf(database.getDailyForecast()[Index].getWindSpeed()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        temp.setText(String.valueOf(0.0) + "°");
        /* wind.setText(String.valueOf(0.0));*/
        icon.setImage(new Image("01d.png"));
    }
}
