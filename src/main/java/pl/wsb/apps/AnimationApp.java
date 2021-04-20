package pl.wsb.apps;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.shape.*;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.Key;

public class AnimationApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        var scene = new Scene(root ,400, 600);

        Circle circle = new Circle(100,100, 50);
        circle.setFill(Color.ALICEBLUE);
        var key1 = new KeyValue(circle.centerXProperty(), 300, Interpolator.EASE_IN);
        var key2 = new KeyValue(circle.fillProperty(), Color.BLUEVIOLET.darker(), Interpolator.EASE_OUT);
        var frame1 = new KeyFrame(Duration.millis(2000), e -> {
            System.out.println("END OF FRAME 1");
        }, key1, key2);
        var frame2 = new KeyFrame(Duration.millis(4000), new KeyValue(circle.centerYProperty(), 400, Interpolator.EASE_BOTH));
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(frame1, frame2);
        timeline.setCycleCount(1);
        timeline.setDelay(Duration.millis(100));
        timeline.play();
        final Rectangle rect = new Rectangle (100, 100, 100, 100);
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setFill(Color.ORANGE);
        rect.getTransforms().add(new Translate(-50, -50));
        rect.setTranslateX(100);
        rect.setTranslateY(100);

        FadeTransition fade = new FadeTransition(Duration.millis(1000), rect);
        fade.setInterpolator(Interpolator.SPLINE(0,1, 0.5, 0.5));
        fade.setFromValue(0.25);
        fade.setToValue(1.0);

        RotateTransition rotate = new RotateTransition(Duration.millis(2000), rect);
        rotate.setFromAngle(0);
        rotate.setToAngle(180);
        rotate.setCycleCount(3);
        rotate.setAutoReverse(true);
        SequentialTransition sequentialTransition = new SequentialTransition();
        var x1 = 0;
        var y1 = 0;
        var x2 = 1;
        var y2 = 0;
        var v1 = 0;
        var v2 =1;
        var duration1 = Duration.millis(1000);
        var duration2 = Duration.millis(2000);
        Interpolator.SPLINE(x1, y1, x2, y2);
        Interpolator.TANGENT(duration1, 1);
        Interpolator.TANGENT(duration1, x1, duration2, x2);
        sequentialTransition.getChildren().addAll(
                rotate,
                fade);
        sequentialTransition.setCycleCount(Timeline.INDEFINITE);
        sequentialTransition.setAutoReverse(true);
        sequentialTransition.play();
        root.getChildren().addAll(circle, rect);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

class AnimationBooleanInterpolator extends Interpolator {
    @Override
    protected double curve(double t) {
        return Math.abs(0.5-t)*2 ;
    }
}
