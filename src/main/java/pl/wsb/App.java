package pl.wsb;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.wsb.app.model.City;
import pl.wsb.service.CitiesService;
import pl.wsb.service.CitiesServiceFile;

import java.util.ArrayList;
import java.util.List;



public class App extends Application {
    private CitiesService citiesService = new CitiesServiceFile("c:\\data\\cities500.txt");
    private ObservableList<City> cities = FXCollections.observableList(citiesService.findAll());
    @Override
    public void start(Stage stage) {
        var root = new BorderPane();
        var leftPanel = new VBox();
        var citiesView = new ListView<City>(cities);
        var button = new Button("Zapisz miasto");
        var nameField = new TextField("City name");
        var countryfield = new TextField("Country code");

        button.setOnAction(e -> {
            String name = nameField.getText();
            String countryCode = countryfield.getText();
            City city = City.builder()
                    .id(cities.size())
                    .name(name)
                    .countryCode(countryCode)
                    .build();
            cities.add(city);
        });
        root.setPadding(new Insets(10));
        root.setCenter(citiesView);
        root.setLeft(leftPanel);
        leftPanel.setSpacing(10);
        leftPanel.setPadding(new Insets(10));
        leftPanel.getChildren().add(nameField);
        leftPanel.getChildren().add(countryfield);
        leftPanel.getChildren().add(button);
        citiesView.getSelectionModel().selectedItemProperty().addListener((ocity, prevCity, currentCity) ->{
                   countryfield.setText(currentCity.getCountryCode());
                   nameField.setText(currentCity.getName());
                });
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Pierwszy program okienkowy");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}