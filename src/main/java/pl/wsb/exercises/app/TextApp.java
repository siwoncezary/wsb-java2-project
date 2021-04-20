package pl.wsb.exercises.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Text text = new Text(0,30, "Przykładowy tekst");
        Font font = Font.font("Arial Black", 30);
        text.setFont(font);
        root.getChildren().add(text);
        Font.getFamilies().forEach(System.out::println);
        Reflection reflection = new Reflection();
        reflection.setFraction(1);
        reflection.setTopOffset(5);
        text.setEffect(reflection);
        Scene scene = new Scene(root ,400, 300);
        stage.setScene(scene);
        stage.show();
    }
}
