package com.uni;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static com.uni.AppController.database;

public class ItemController implements Initializable {
    @FXML
    private Label temp;
    @FXML
    private Label wind;
    @FXML
    private ImageView icon;

    public void update(int Index){
        temp.setText(String.valueOf(database.getHourlyForecast()[Index].getTemp()));
        System.out.println(database.getHourlyForecast()[Index].getIdIcon() + ".png");
        icon.setImage(new Image(database.getHourlyForecast()[Index].getIdIcon() + ".png"));
        wind.setText(String.valueOf(database.getHourlyForecast()[Index].getWindSpeed()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        temp.setText(String.valueOf(0.0));
        wind.setText(String.valueOf(0.0));
        icon.setImage(new Image("01d.png"));
    }
}
