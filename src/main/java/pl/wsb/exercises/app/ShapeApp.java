package pl.wsb.exercises.app;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class ShapeApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = prepareRoot(stage);
        Polygon polygon = new Polygon(
                100, 100,
                200, 200,
                150, 200,
                200, 100,
                100, 100);
        root.getChildren().add(polygon);
        Path arrow = new Path();
        arrow.setStrokeWidth(10);
        arrow.setStrokeLineCap(StrokeLineCap.ROUND);
        arrow.getElements().addAll(
                new MoveTo(200, 200),
                new LineTo(150, 250),
                new MoveTo(200, 200),
                new LineTo(250, 250),
                new MoveTo(200, 200),
                new LineTo(200, 400)
        );
        arrow.setTranslateY(100);
        arrow.setTranslateY(50);
        arrow.setScaleX(0.5);
        arrow.setScaleY(0.8);
        arrow.setRotate(90);
        root.getChildren().add(arrow);
        Rectangle rectangle = new Rectangle(0,0, 300, 200);
        LinearGradient gradient = new LinearGradient(
            0,
            0,
                300,
                0,
                false,
                CycleMethod.NO_CYCLE,
                new Stop(0.2, Color.WHITE),
                new Stop( 0.8, Color.CHOCOLATE)
        );
        rectangle.setFill(gradient);
        root.getChildren().add(rectangle);
    }

    public static void main(String[] args) {
        launch();
    }

    private Group prepareRoot(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Bouncing balls");
        stage.show();
        return root;
    }
}
