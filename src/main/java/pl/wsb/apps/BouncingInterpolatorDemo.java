package pl.wsb.apps;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BouncingInterpolatorDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Circle circle = new Circle(100,100, 50);
        TranslateTransition trans = new TranslateTransition(Duration.millis(1000));
        trans.setInterpolator(CustomInterpolator.BOUNCING);
        trans.setDuration(Duration.millis(1000));
        trans.setNode(circle);
        trans.setFromX(100);
        trans.setToX(400);
        trans.play();

        root.getChildren().add(circle);
        var scene = new Scene(root, 800, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
