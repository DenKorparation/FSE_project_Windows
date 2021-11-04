package com.uni;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button EnterCity;


    @FXML
    void display(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert EnterCity != null : "fx:id=\"EnterCity\" was not injected: check your FXML file 'App.fxml'.";

    }

}


