package pl.wsb.exercises.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleAnimationApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        var root = new Group();
        var scene = new Scene(root, 400, 600);
        Rectangle rect = new Rectangle(100, 100, 200, 100);
        rect.setOnMouseClicked(e -> {
            rect.setFill(Color.BLUE);
        });
        rect.setFill(Color.BLUEVIOLET);
        rect.setStroke(Color.BROWN);
        rect.setStrokeWidth(3);
        root.getChildren().add(rect);
        stage.setScene(scene);
        stage.show();
        double distanceX = 100;
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            double finalX = rect.getX() + distanceX;
            while (rect.getX() != finalX) {
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                }
                rect.setX(rect.getX() + 2);
            }
        });
        service.shutdown();
    }
}
