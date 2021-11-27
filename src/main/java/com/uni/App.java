package com.uni;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * App class launches the main window
 * @ Sasha
 * @ version 1.1
 */

public class App extends Application {
    AppController mainSceneController = new AppController();

    /**
     * method which loads a primary stage
     * @param primaryStage sets a primaryStage
     * @throws IOException if can't get the file
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/App.fxml"));
        Parent root = loader.load();
        mainSceneController = loader.getController();
        primaryStage.setTitle("Gismeteostats");
        primaryStage.setScene(new Scene(root, 1100 ,  650));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("https://www.1rre.ru/wp-content/uploads/2021/04/f4a3fbd4db51edf098a710dc05c3c53b.jpg"));
        primaryStage.show();
        mainSceneController.postInit();
    }

    /**
     * method launches the program
     * @param args argument of launch
     */

    public static void main(String[] args) {
        launch();
    }
}


