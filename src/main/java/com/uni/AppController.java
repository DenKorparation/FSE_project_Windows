package com.uni;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * AppController class that implements methods connected with interface
 * @author Sasha
 * @version 1.0
 */
public class AppController {
    /**
     * database stores information about forecast and location
     */
    public static Database database = new Database();
    /**
     * EnterButton Button starts the program
     */
    @FXML
    private Button EnterButton;
    /**
     * EnterCity TextField saves the entered city value
     */
    @FXML
    private TextField EnterCity;
    /**
     * lblForecastfor7days Label which contains "Forecast for 7 days" text
     */
    @FXML
    private Label lblForecastfor7days;
    /**
     * Temp Label for temperature's output
     */
    @FXML
    private Label Temp;
    /**
     * feelslikatemp Label for feels like temperature's output
     */
    @FXML
    private Label feelslikeTemp;
    /**
     * humidity Label for humidity's output
     */
    @FXML
    private Label humidity;
    /**
     * lbl Label
     */
    @FXML
    private Label lbl;
    /**
     * curTime Label for current time output
     */
    @FXML
    private Label curTime;
    /**
     * pressure Label for pressure's output
     */
    @FXML
    private Label pressure;
    /**
     * mainIm ImageView for main image's output
     */
    @FXML
    private ImageView mainIm;
    /**
     * condition Label for main condition's output
     */
    @FXML
    private Label condition;
    /**
     * windspeed Label for speed's of wind output
     */
    @FXML
    private Label windSpeed;
    /**
     * curCond String which gets current condition value from database
     */
    @FXML
    private String curCond;
    /**
     * partOfDay String which gets partOfDay value from database
     */
    @FXML
    private String partOfDay;

    @FXML
    private Label result_info;
    /**
     * humidityIm ImageView for humidity image output
     */
    @FXML
    private ImageView humidityIm;
    /**
     * windIm ImageView for wind image output
     */
    @FXML
    private ImageView windIm;
    /**
     * pressureIm ImageView for pressure image output
     */
    @FXML
    private ImageView pressureIm;
    /**
     * minus ImageView for minus image output
     */
    @FXML
    private ImageView minus;
    /**
     * plus ImageView for plus image output
     */
    @FXML
    private ImageView plus;
    /**
     * mainIcon ImageView for main icon output
     */
    @FXML
    private ImageView mainIcon;
    /**
     * magnifer ImageView for magnifer image output
     */
    @FXML
    private ImageView magnifier;
    /**
     * DailuWeatherList AnchorPane locates blocks of daily weather
     */
    @FXML
    private AnchorPane DailyWeatherList;
    /**
     * Map ImageView for Map image output
     */
    @FXML
    private ImageView Map;
    /**
     * cloudsBut Button which on a click stars handle method
     */
    @FXML
    private Button cloudsBut;
    /**
     * line Line wich outputs the line
     */
    @FXML
    private Line line;
    /**
     * precipBut Button which on a click stars handle method
     */
    @FXML
    private Button precipBut;
    /**
     * pressureBut Button which on a click stars handle method
     */
    @FXML
    private Button pressureBut;
    /**
     * tempBut Button which on a click stars handle method
     */
    @FXML
    private Button tempBut;
    /**
     * windspeed Button which on a click stars handle method
     */
    @FXML
    private Button windspeedBut;
    /**
     * hourlyWeatherList AnchorPane contains blocks with information
     */
    @FXML
    private AnchorPane hourlyWeatherList;
    /**
     * hForecastScroll which contains scroll
     */
    @FXML
    private ScrollBar hForecastScroll;
    /**
     * weatherMap ImageView for weather map output
     */
    @FXML
    private ImageView weatherMap;
    /**
     * legend ImageView for legend of maps output
     */
    @FXML
    private ImageView legend;
    /**
     * lblmaps Label which contains "map of weather" text
     */
    @FXML
    private Label lblmaps;
    /**
     * lblForecastforday Label which contains "Forecast for day" text
     */
    @FXML
    private Label lblForecastforday;

    /**
     * nodeHourly stores list of blocks about hourly forecast
     */
    private Node[] nodeHourly = new Node[48];
    /**
     * nodesHourluController stores controller for capability to change information in blocks
     */
    private  ItemController[] nodesHourlyController = new ItemController[48];
    /**
     * nodeDaily stores list of blocks about daily forecast
     */
    private Node[] nodeDaily = new Node[7];
    /**
     * nodesDailyController stores controller for capability to change information in blocks
     */
    private  dailyItemController[] nodesDailyController = new dailyItemController[7];

    /**
     * OnClickMethod when enter button is clicked starts the main code
     */
    @FXML
    void OnClickMethod() {
        database.setNameOfCity(EnterCity.getText());
        result_info.setText("Ожидайте...");
        Thread t = new Thread(() -> {
            database.request();

            Platform.runLater(() -> {
                if(database.isCorrectData()){
                    line.setVisible(true);
                    lblForecastforday.setVisible(true);
                    lblForecastfor7days.setVisible(true);
                    hForecastScroll.setVisible(true);
                    tempBut.setVisible(true);
                    cloudsBut.setVisible(true);
                    precipBut.setVisible(true);
                    pressureBut.setVisible(true);
                    windspeedBut.setVisible(true);
                    Map.setVisible(true);
                    weatherMap.setVisible(true);
                    DailyWeatherList.setVisible(true);
                    hourlyWeatherList.setVisible(true);
                    minus.setVisible(true);
                    plus.setVisible(true);
                    lblmaps.setVisible(true);

                    lbl.setText("Город " + database.getNameOfCity() + ", " + database.getCodeOfCountry());
                    String temp = (database.getCurWeatherData().getTemp()) +  "°";
                    Temp.setText(temp);
                    feelslikeTemp.setText("Ощущается как " + (database.getCurWeatherData().getFeelsLikeTemp())  +  "°");
                    windSpeed.setText((database.getCurWeatherData().getWindSpeed()) + " м/c");
                    humidity.setText( (database.getCurWeatherData().getHumidity()) + "%");
                    pressure.setText((database.getCurWeatherData().getPressure()) + " hPa");

                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM HH:mm z"); // какой формат нужен, выбераем
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT+3")); // если нужно даем таймзон
                    curTime.setText("Состояние на  " + (sdf.format(database.getCurWeatherData().getTime())));
                    humidityIm.getImage();
                    humidityIm.setImage(new Image("humidityIm.png"));
                    windIm.getImage();
                    windIm.setImage(new Image("wind.png"));
                    pressureIm.getImage();
                    pressureIm.setImage(new Image("pressure.png"));
                    magnifier.setImage(new Image("magnifier.png"));
                    mainIm.getImage();
                    mainIcon.getImage();
                    curCond = database.getCur_Condition();
                    partOfDay = database.getPartOfDay();
                    condition.setText(database.getCurWeatherData().getDescription());

                    if ((Objects.equals(curCond, "Clear"))  & (Objects.equals(partOfDay, "night"))) {
                        mainIm.setImage(new Image("night.jpg"));
                        mainIcon.setImage(new Image("01n.png"));
                    }
                    if ((Objects.equals(curCond, "Clouds")) & (Objects.equals(partOfDay, "night"))) {
                        mainIm.setImage(new Image("night.jpg"));
                        mainIcon.setImage(new Image("04n.png"));
                    }

                    if ((Objects.equals(curCond, "Clear")) & (Objects.equals(partOfDay, "day"))) {
                        mainIm.setImage(new Image("clear.jpg"));
                        mainIcon.setImage(new Image("01d.png"));
                    }
                    if ((Objects.equals(curCond, "Fog")) & (Objects.equals(partOfDay, "day"))) {
                        mainIm.setImage(new Image("fog.jpg"));
                        mainIcon.setImage(new Image("50d.png"));
                    }
                    if ((Objects.equals(curCond, "Fog")) & (Objects.equals(partOfDay, "night"))) {
                        mainIm.setImage(new Image("fog.jpg"));
                        mainIcon.setImage(new Image("50n.png"));
                    }
                    if (Objects.equals(curCond, "Clouds") & (Objects.equals(partOfDay, "day"))) {
                        mainIm.setImage(new Image("clouds.jpg"));
                        mainIcon.setImage(new Image("02d.png"));
                    }
                    if (Objects.equals(curCond, "Rain") || (Objects.equals(curCond, "Drizzle"))) {
                        mainIm.setImage(new Image("rain.jpg"));
                        mainIcon.setImage(new Image("09d.png"));
                    }
                    if (Objects.equals(curCond, "Snow")) {
                        mainIm.setImage(new Image("snow.jpg"));
                        mainIcon.setImage(new Image("13d.png"));
                    }
                    if (Objects.equals(curCond, "Thunderstorm")) {
                        mainIm.setImage(new Image("thunder.jpg"));
                        mainIcon.setImage(new Image("11d.png"));
                    }

                    for (int i = 0; i < nodeHourly.length; i++){
                        nodesHourlyController[i].updateHourly(i);
                    }

                    for (int i = 0; i < nodeDaily.length; i++){
                        nodesDailyController[i].updateDaily(i);
                    }


                    result_info.setText("");
                }
                else{
                    result_info.setText("Такого города не сущетсвует");
                }
                minus.getImage();
                plus.getImage();
                minus.setImage(new Image("minus.png"));
                plus.setImage(new Image("plus.png"));
                Map.setImage(database.getMap());
                weatherMap.setImage(database.getWeatherMap());
                legend.setImage(new Image("pressure_new.png"));
            });
        });
        t.start();

    }

    /**
     * method initialization of elements
     */
    @FXML
    void initialize() {
        line.setVisible(false);
        lblForecastforday.setVisible(false);
        hForecastScroll.setVisible(false);
        tempBut.setVisible(false);
        cloudsBut.setVisible(false);
        precipBut.setVisible(false);
        pressureBut.setVisible(false);
        windspeedBut.setVisible(false);
        Map.setVisible(false);
        lblForecastfor7days.setVisible(false);
        weatherMap.setVisible(false);
        DailyWeatherList.setVisible(false);
        hourlyWeatherList.setVisible(false);
        minus.setVisible(false);
        plus.setVisible(false);
        lblmaps.setVisible(false);

        for (int i=0; i<48; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/item.fxml"));
                nodeHourly[i] = loader.load();
                nodesHourlyController[i] = loader.getController();
                hourlyWeatherList.getChildren().add(nodeHourly[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        for (int i=0; i<7; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dailyItem.fxml"));
                nodeDaily[i] = loader.load();
                nodesDailyController[i] = loader.getController();
                DailyWeatherList.getChildren().add(nodeDaily[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        hForecastScroll.setMin(0);
        hForecastScroll.setMax(1);
        hForecastScroll.setValue(0);
        hForecastScroll.valueProperty().addListener(new ChangeListener<Number>() {
            /**
             * method  that updates information during scroll
             */
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                for (int i = 0; i < nodeHourly.length; i++) {
                    double node_width = nodeHourly[i].getLayoutBounds().getWidth();
                    nodeHourly[i].setTranslateX(node_width * 1.2 * i - (node_width * 1.2 * nodeHourly.length - 0.2 * node_width - hourlyWeatherList.getLayoutBounds().getWidth()) * hForecastScroll.getValue());
                }
            }
        });
        tempBut.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            /**
             * method that on a click sets a temperature map
             * @param mouseEvent event action that starts the action
             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                database.setMapLayer("temp_new");
                Thread t = new Thread(() -> {

                    database.reqMap();
                    Platform.runLater(() -> {
                        Map.setImage(database.getMap());
                        weatherMap.setImage(database.getWeatherMap());
                        legend.setImage(new Image("temp_new.png"));

                    });
                });
                t.start();
            }
        });
        /**
         * method that on a click sets a pressure map
         * @param mouseEvent event action that starts the action
         */
        pressureBut.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                database.setMapLayer("pressure_new");

                Thread t = new Thread(() -> {
                    database.reqMap();
                    Platform.runLater(() -> {
                        Map.setImage(database.getMap());
                        weatherMap.setImage(database.getWeatherMap());
                        legend.setImage(new Image("pressure_new.png"));

                    });
                });
                t.start();
            }
        });

        cloudsBut.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            /**
             * method that on a click sets a clouds map
             * @param mouseEvent event action that starts the action
             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                database.setMapLayer("clouds_new");
                Thread t = new Thread(() -> {
                    database.reqMap();
                    Platform.runLater(() -> {
                        Map.setImage(database.getMap());
                        weatherMap.setImage(database.getWeatherMap());
                        legend.setImage(new Image("clouds_new.png"));

                    });
                });
                t.start();
            }
        });

        windspeedBut.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            /**
             * method that on a click sets a wind speed map
             * @param mouseEvent event action that starts the action
             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                database.setMapLayer("wind_new");

                Thread t = new Thread(() -> {
                    database.reqMap();
                    Platform.runLater(() -> {
                        Map.setImage(database.getMap());
                        weatherMap.setImage(database.getWeatherMap());
                        legend.setImage(new Image("wind_new.png"));

                    });
                });
                t.start();
            }
        });
        precipBut.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            /**
             * method that on a click sets a precipitation map
             * @param mouseEvent event action that starts the action
             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                database.setMapLayer("precipitation_new");

                Thread t = new Thread(() -> {
                    database.reqMap();
                    Platform.runLater(() -> {
                        Map.setImage(database.getMap());
                        weatherMap.setImage(database.getWeatherMap());
                        legend.setImage(new Image("precipitation_new.png"));

                    });
                });
                t.start();
            }
        });
        plus.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            /**
             * method that on a click zooms a map
             * @param mouseEvent event action that starts the action
             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                database.zoomIncrement();
                Thread t = new Thread( () -> {
                    database.reqMap();
                    Platform.runLater(() -> {
                        Map.setImage(database.getMap());
                        weatherMap.setImage(database.getWeatherMap());
                    });
                });
                t.start();
            }
        });
        minus.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            /**
             * method that on a click zooms a map
             * @param mouseEvent event action that starts the action
             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                database.zoomDecrement();
                database.reqMap();
                Map.setImage(database.getMap());
                weatherMap.setImage(database.getWeatherMap());

            }
        });

    }

    /**
     * method that locates blocks of information on AnchorPane
     */
    public void postInit(){
        for(int i = 0; i < nodeHourly.length; i++) {
            nodeHourly[i].setTranslateX(nodeHourly[i].getLayoutBounds().getWidth() * 1.2 * i);
            AnchorPane.setTopAnchor(nodeHourly[i], (hourlyWeatherList.getHeight() - nodeHourly[i].getLayoutBounds().getHeight()) / 2);
            System.out.println(hourlyWeatherList.getHeight() - nodeHourly[i].getLayoutBounds().getHeight() / 2);
        }
        Rectangle hclip = new Rectangle(hourlyWeatherList.getWidth(), hourlyWeatherList.getHeight());
        hourlyWeatherList.setClip(hclip);

        for(int i = 0; i < nodeDaily.length; i++) {
            nodeDaily[i].setTranslateX(nodeDaily[i].getLayoutBounds().getWidth() * 1.1 * i);
            AnchorPane.setTopAnchor(nodeDaily[i], (DailyWeatherList.getHeight() - nodeDaily[i].getLayoutBounds().getHeight()) / 2);
            System.out.println(DailyWeatherList.getHeight() - nodeDaily[i].getLayoutBounds().getHeight() / 2);
        }
        Rectangle dclip = new Rectangle(DailyWeatherList.getWidth(), DailyWeatherList.getHeight());
        DailyWeatherList.setClip(dclip);
    }

}