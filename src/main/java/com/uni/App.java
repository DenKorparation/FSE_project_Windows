package com.uni;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class App extends Application {
    AppController mainSceneController = new AppController();
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/App.fxml"));
        Parent root = loader.load();
        mainSceneController = loader.getController();
        primaryStage.setTitle("Gismeteostats");
        primaryStage.setScene(new Scene(root, 1100 ,  650));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("https://ds-blobs-2.cdn.devapps.ru/6380085.png"));
        primaryStage.show();
        mainSceneController.postInit();
    }



    public static void main(String[] args) {
        launch();
    }
}


