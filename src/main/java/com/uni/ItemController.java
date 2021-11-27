package com.uni;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import static com.uni.AppController.database;

/** ItemlController class that implements methods connected with interface( implements {@link Initializable} class)
 * @author Sasha
 * @version 1.1
 */
public class ItemController implements Initializable {
    /**
     * temp Label which gets temperature value from database
     */
    @FXML
    private Label temp;
    /**
     * time Label which gets time value from database
     */
    @FXML
    private Label time;
    /**
     * icon ImageView for icon image output
     */
    @FXML
    private ImageView icon;
    /**
     * feelsliketemp Label which gets feels like temperature value from database
     */
    @FXML
    private Label feelsliketemp;
    /**
     * curCond Label which gets current Condition value from database
     */
    @FXML
    private Label curCond;
    /**
     * Index int which gets index of an hour
     */
    private int Index;

    /**
     * moreInfoController controller of More info window to change information in this window
     */
    InfoClass moreInfoController;

    /**
     * method that gets all the information about hourly forecast from database
     * @param Index the number of an hour
     */
    public void updateHourly(int Index){
        this.Index = Index;
        temp.setText("Температура " + String.valueOf(database.getHourlyForecast()[Index].getTemp()) + "°");
        System.out.println(database.getHourlyForecast()[Index].getIdIcon() + ".png");
        icon.setImage(new Image(database.getHourlyForecast()[Index].getIdIcon() + ".png"));
        feelsliketemp.setText("Ощущается как " + (String.valueOf(database.getHourlyForecast()[Index].getFeelsLikeTemp())) + "°" );
        SimpleDateFormat sdf = new SimpleDateFormat("E. dd.MM HH:mm ");
        /*sdf.setTimeZone(TimeZone.getTimeZone("GMT+3"))*/;
        time.setText((sdf.format(database.getHourlyForecast()[Index].getTime())));
        curCond.setText(database.getHourlyForecast()[Index].getDescription());
    }

    /**
     * method which loadss scene with more information
     * @throws IOException is can't get the file
     */

    @FXML
    void ToMoreInfo() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/info.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("info.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("More Info");
        stage.setResizable(false);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        moreInfoController = loader.getController();
        moreInfoController.updateHourly(Index);
    }

    /**
     * method that initializes elements of hourly forecast
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        temp.setText(String.valueOf(0.0)+ "°");
        icon.setImage(new Image("01d.png"));
    }
}
