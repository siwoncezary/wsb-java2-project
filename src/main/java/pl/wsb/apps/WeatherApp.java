package pl.wsb.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WeatherApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var root = new VBox();
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("file:///c:\\data\\weather\\weather-cloud.png"));
        root.getChildren().add(imageView);
        var scene= new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
