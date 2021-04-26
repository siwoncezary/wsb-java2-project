package pl.wsb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.wsb.app.model.City;
import pl.wsb.service.CitiesService;
import pl.wsb.service.CitiesServiceFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class CityApp extends Application {
    private CitiesService citiesService = new CitiesServiceFile("c:\\data\\cities500.txt");
    private ObservableList<City> cities = FXCollections.observableList(citiesService.findAll());
    private ObservableList<String> countryCodes = FXCollections.observableList(
            cities.stream().map(city -> city.getCountryCode()).distinct().collect(Collectors.toList())
    );
    private TableView<City> citiesView;
    private HBox filterPanel;
    private HBox weatherPanel;
    private SimpleStringProperty cityNameProperty;
    private SimpleStringProperty cityLongitudeProperty;
    private SimpleStringProperty cityLatitudeProperty;
    private HttpClient client = HttpClient.newHttpClient();


    public CityApp() {
        countryCodes.add(0, "ALL");
        cityLongitudeProperty = new SimpleStringProperty();
        cityNameProperty = new SimpleStringProperty();
        cityLatitudeProperty = new SimpleStringProperty();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        citiesView = createCitiesView();
        filterPanel = createFilterPanel();
        weatherPanel = createWeatherPanel();

        citiesView.setItems(cities);
        root.setTop(filterPanel);
        root.setCenter(citiesView);
        root.setBottom(weatherPanel);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public TableView createCitiesView(){
        TableView<City> view = new TableView<>();
        TableColumn<City, String> names = new TableColumn<>("Nazwa");
        TableColumn<City, String> codes = new TableColumn<>("Kod");
        TableColumn<City, Integer> populations = new TableColumn<>("Populacja");
        TableColumn<City, Double> longitudes = new TableColumn<>("długość geog.");
        TableColumn<City, Double> latitudes = new TableColumn<>("szerokość geog.");
        view.getColumns().addAll(names, codes, populations, latitudes, longitudes);
        names.setCellValueFactory(new PropertyValueFactory<>("name"));
        codes.setCellValueFactory(new PropertyValueFactory<>("countryCode"));
        populations.setCellValueFactory(new PropertyValueFactory<>("population"));
        longitudes.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        latitudes.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        view.getSelectionModel().selectedItemProperty().addListener((v, prevCity, currCity) ->{
            cityNameProperty.set(currCity.getName());
            cityLatitudeProperty.set(Double.toString(currCity.getLatitude()));
            cityLongitudeProperty.set(""+currCity.getLongitude());
        });
        return view;
    }

    public HBox createFilterPanel(){
        HBox box = new HBox();
        box.setPadding(new Insets(10));
        box.setSpacing(10);
        Label codesLabel = new Label("Kody krajów");
        ComboBox<String> codesBox = new ComboBox<>();
        codesLabel.setLabelFor(codesBox);
        codesBox.setItems(countryCodes);
        box.getChildren().addAll(codesLabel,codesBox);
        codesBox.setOnAction(e -> {
            String countryCode = codesBox.getSelectionModel().getSelectedItem();
            if(countryCode.equals("ALL")){
                citiesView.setItems(FXCollections.observableList(cities));
                return;
            }
            List<City> filteredCities = this.cities.stream().filter(city -> city.getCountryCode().equals(countryCode)).collect(Collectors.toList());
            citiesView.setItems(FXCollections.observableList(filteredCities));
        });
        return box;
    }

    public HBox createWeatherPanel(){
        HBox root = new HBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        TextField cityName = new TextField();
        TextField cityLatitude = new TextField();
        TextField cityLongitude = new TextField();
        cityName.setEditable(false);
        cityLongitude.setEditable(false);
        cityLatitude.setEditable(false);
        cityName.textProperty().bindBidirectional(cityNameProperty);
        cityLongitude.textProperty().bindBidirectional(cityLongitudeProperty);
        cityLatitude.textProperty().bindBidirectional(cityLatitudeProperty);
        Button action = new Button("Pobierz");
        TextArea jsonView = new TextArea();
        Label tempLabel = new Label("Temperatura");
        tempLabel.setFont(Font.font("Arial", 30));
        action.setOnAction(e ->{
            try {
                HttpRequest request = HttpRequest.newBuilder(
                        new URI("http://api.openweathermap.org/data/2.5/weather?q="
                        + cityNameProperty.get().trim()
                        + "&appid=615d3ad8681ea723781b4255adda0859"))
                        .GET()
                        .build();
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .whenCompleteAsync((response, exception) ->{
                            jsonView.setText(response.body());
                            ObjectMapper mapper = new ObjectMapper();
                            System.out.println("Mapper");
                            try {
                                JsonNode tree = mapper.readTree(response.body());
                                double temp = tree.get("main").get("temp").asDouble();
                                double temp_feel = tree.get("main").get("feels_like").asDouble();
                                Platform.runLater(() -> {
                                    tempLabel.setText("Temperatura: " + Double.toString(temp));
                                });
                            } catch (JsonProcessingException jsonProcessingException) {
                                System.out.println(jsonProcessingException.getMessage());
                            }

                        });
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }
        });
        root.getChildren().addAll(cityName, cityLatitude, cityLongitude, action, tempLabel, jsonView);
        return root;
    }

}
