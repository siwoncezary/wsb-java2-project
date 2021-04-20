package pl.wsb.apps;

import javafx.application.Application;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;
import lombok.extern.java.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Log
public class ClickMeGame extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var root = new Group();
        var scene = new Scene(root, 800, 600);
        var target = new Ellipse(100,100, 50, 50);
        var curve = new CubicCurve(0, 300, 100, 400, 400, 100, 800, 300);
        curve.setFill(Color.TRANSPARENT);
        curve.setStroke(Color.BLUEVIOLET);
        root.getChildren().addAll(target, curve);
        boolean[] isTarget = {false};
        scene.setOnMousePressed(e -> {
            final double dx = Math.abs(e.getSceneX()-target.getCenterX());
            final double dy = Math.abs(e.getSceneY() - target.getCenterY());
             if ( dx < 25 &&  dy < 25){
                 isTarget[0] = true;
             }
        });
        scene.setOnMouseDragged(e -> {
            if (isTarget[0]) {
                target.setCenterX(e.getSceneX());
                target.setCenterY(e.getSceneY());
                BoundingBox box = new BoundingBox(target.getCenterX()-25, target.getCenterY()-25, target.getCenterX()+25, target.getCenterY()+25);
                log.info("" + box);
                Point2D point = curve.localToScene(new Point2D(e.getSceneX(), e.getSceneY()));
                log.info("POINT " + point);
                if (curve.intersects(box)){
                    log.info("INTERSECT");
                }
            }
        });

        scene.setOnMouseReleased(e -> {
            isTarget[0] = false;
        });
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}
