package pl.wsb;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var root = new Group();
        root.getChildren().add(new ListView<String>(FXCollections.observableList(List.of("AAA","BBB","CCC"))));
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Pierwszy program okienkowy");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}