package com.uni;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.TimeZone;

import static com.uni.AppController.database;

public class ItemController implements Initializable {
    @FXML
    private Label temp;
    @FXML
    private Label time;
    @FXML
    private ImageView icon;
    @FXML
    private Label feelsliketemp;
    @FXML
    private Label curCond;
    @FXML
    private Button MoreInfo;

    public void update(int Index){
        temp.setText("Температура " + String.valueOf(database.getHourlyForecast()[Index].getTemp()) + "°");
        System.out.println(database.getHourlyForecast()[Index].getIdIcon() + ".png");
        icon.setImage(new Image(database.getHourlyForecast()[Index].getIdIcon() + ".png"));
        feelsliketemp.setText("Ощущается как " + (String.valueOf(database.getHourlyForecast()[Index].getFeelsLikeTemp())) + "°" );
        /*wind.setText(String.valueOf(database.getHourlyForecast()[Index].getWindSpeed()));*/
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM HH:mm z");
       /* sdf.setTimeZone(TimeZone.getTimeZone("GMT+3"));*/
        time.setText((sdf.format(database.getHourlyForecast()[Index].getTime())));
        curCond.setText(database.getHourlyForecast()[Index].getCondition());
    }
    @FXML
    void ToMoreInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/info.fxml"));
        Stage stage = new Stage();
        stage.setTitle("More Info");
        stage.setResizable(false);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        temp.setText(String.valueOf(0.0)+ "°");
       /* wind.setText(String.valueOf(0.0));*/
        icon.setImage(new Image("01d.png"));
    }
}
