package com.uni;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static Database database = new Database();
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/App.fxml"));
        primaryStage.setTitle("Gismeteostats");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.getIcons().add(new Image("https://ds-blobs-2.cdn.devapps.ru/6380085.png"));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}


