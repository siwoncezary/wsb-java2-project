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

import java.util.ArrayList;
import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {
    private List<City> listCity = new ArrayList<>(List.of(
      new City(1, "Cieszyn", "PL", 35_000),
      new City(2, "Paryż", "FR", 4_000_000),
      new City(3, "New York", "US", 10_000_000)
    ));
    private ObservableList<City> cities = FXCollections.observableList(listCity);

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
            City city = new City(cities.size(), name, countryCode, 1000);
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