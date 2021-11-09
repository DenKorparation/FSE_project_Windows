package com.uni;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button EnterButton;

    @FXML
    private TextField EnterCity;

    @FXML
    private Label lbl;

    @FXML
    private Label Temp;

    @FXML

    public void OnClickMethod() {
        EnterButton.setOnAction(event -> lbl.setText("Город: " + EnterCity.getText()));
        EnterButton.setText("Clicked");
        App.database.setNameOfCity(lbl.getText());
        App.database.request();

    }

    @FXML
    void initialize() {

}




}