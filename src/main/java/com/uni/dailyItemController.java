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
    private ImageView TempIm;
    @FXML
    private ImageView icon;
    @FXML
    private ImageView wind_icon;

    private int Index;

    public void updateDaily(int Index){
        this.Index = Index;
        TempIm.getImage();
        TempIm.setImage(new Image("temp.png"));
        temp.setText(String.valueOf(database.getDailyForecast()[Index].getTempDay()) + " °/ " + (database.getDailyForecast()[Index].getTempNight() + "°"));
        System.out.println(database.getDailyForecast()[Index].getIdIcon() + ".png");
        icon.setImage(new Image(database.getDailyForecast()[Index].getIdIcon() + ".png"));

        feelsliketemp.setText("Ощущается как " + (String.valueOf(database.getDailyForecast()[Index].getFeelsLikeTempDay())) + "°/ " + (database.getDailyForecast()[Index].getFeelsLikeTempNight()) + "°");
        feelsliketemp.setLineSpacing(0.0);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd.MM");
        /* sdf.setTimeZone(TimeZone.getTimeZone("GMT+3"));*/
        time.setText((sdf.format(database.getDailyForecast()[Index].getTime())));
        curCond.setText(database.getDailyForecast()[Index].getDescription());
        windspeed.setText(String.valueOf(database.getDailyForecast()[Index].getWindSpeed()) + " м/с");
        wind_icon.setImage(new Image("wind_icon.png"));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        temp.setText(Double.valueOf(0.0) + "°");
        /* wind.setText(String.valueOf(0.0));*/
        icon.setImage(new Image("01d.png"));
    }
}
