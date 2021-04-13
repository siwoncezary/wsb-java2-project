package pl.wsb;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
        var root = new VBox();
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

        root.getChildren().add(citiesView);
        root.getChildren().add(button);
        root.getChildren().add(nameField);
        root.getChildren().add(countryfield);
        citiesView.getSelectionModel().selectedItemProperty().addListener((ocity, prevCity, currentCity) ->{
                    System.out.println(currentCity.getPopulation() + " " + currentCity.getCountryCode());
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