package pl.wsb.exercises.app;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Button startAnimation = new Button("START");
        Circle circle = new Circle(100, 100, 100);
        KeyValue withValue = new KeyValue(circle.centerXProperty(), 200, Interpolator.EASE_IN);
        KeyValue centerYValue = new KeyValue(circle.centerYProperty(), 200, Interpolator.EASE_OUT);
        KeyFrame circleFrame = new KeyFrame(Duration.millis(1000), withValue, centerYValue);
        Timeline timeline = new Timeline(circleFrame);
        timeline.setAutoReverse(true);
        timeline.setCycleCount(6);
        Rectangle rectangle = new Rectangle(200, 200, 200, 200);
        rectangle.setFill(Color.BLUEVIOLET);
        //rectangle.getTransforms().add(new Translate(-100,-100));
        FadeTransition rectFade = new FadeTransition(Duration.millis(2000), rectangle);
        rectFade.setFromValue(0);
        rectFade.setToValue(1);
        ScaleTransition rectScale = new ScaleTransition(Duration.millis(2000), rectangle);
        rectScale.setFromX(0);
        rectScale.setFromY(0);
        rectScale.setToX(1);
        rectScale.setToY(1);
        RotateTransition rectRotate = new RotateTransition(Duration.millis(2000), rectangle);
        rectRotate.setAxis(new Point3D(1, 0, 1));
        rectRotate.setFromAngle(0);
        rectRotate.setToAngle(360);
        Rectangle box = new Rectangle(100,100, 100, 100);
        box.setFill(Color.CADETBLUE);
        FadeTransition boxFade = new FadeTransition(Duration.millis(1000), box);
        boxFade.setFromValue(0);
        boxFade.setToValue(1);
        TranslateTransition boxTranslate = new TranslateTransition(Duration.millis(2000), box);
        boxTranslate.setFromX(100);
        boxTranslate.setToX(400);
        boxTranslate.setInterpolator(Interpolator.SPLINE(0, 0, 0.8, 0));
        PathTransition rectPath = new PathTransition(Duration.millis(2000), box);
        Path path = new Path();
        path.getElements().addAll(
                new MoveTo(200, 200),
                new CubicCurveTo(200,200, 250, 300, 300, 200)
        );
        rectPath.setPath(path);
        rectPath.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        ParallelTransition transition = new ParallelTransition();
        transition.getChildren().addAll(rectPath);
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(boxTranslate, boxFade);
        startAnimation.setOnAction(e -> {
            timeline.playFromStart();
            transition.play();
            sequentialTransition.play();
        });
        root.getChildren().addAll(circle, startAnimation, rectangle, box);
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
