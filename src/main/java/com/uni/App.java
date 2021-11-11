package com.uni;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/App.fxml"));
       /* Image image = new Image(new FileInputStream("src/main/java/assets/sky.jpg"));
        ImageView imageView1 = new ImageView(image);
        imageView1.setImage(image);
        imageView1.setX(50);
        imageView1.setY(25);
        imageView1.setFitHeight(300);
        imageView1.setFitWidth(250);*/



        primaryStage.setTitle("Gismeteostats");
        primaryStage.setScene(new Scene(root, 1100 ,  650));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("https://ds-blobs-2.cdn.devapps.ru/6380085.png"));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}


