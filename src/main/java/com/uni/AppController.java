package com.uni;




import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField EnterCity;

    @FXML
    void initialize() {
        assert EnterCity != null : "fx:id=\"EnterCity\" was not injected: check your FXML file 'App.fxml'.";

    }

}


