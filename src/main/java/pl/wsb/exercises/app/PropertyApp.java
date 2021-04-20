package pl.wsb.exercises.app;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PropertyApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        SimpleDoubleProperty widthProperty = new SimpleDoubleProperty();
        Rectangle rect1 = new Rectangle(100, 100,200, 50);
        Rectangle rect2 = new Rectangle(100, 200, 200, 50);
        rect1.widthProperty().bindBidirectional(widthProperty);
        //High level binding
        //rect2.widthProperty().bind(widthProperty.subtract(50));
        //Low level binding
        DoubleBinding withBinding = new DoubleBinding() {
            {
                super.bind(widthProperty);
            }
            @Override
            protected double computeValue() {
                return widthProperty.get() - 50;
            }
        };
        rect2.widthProperty().bind(withBinding);
        widthProperty.set(50);
        rect1.setWidth(200);
        Circle circle = new Circle(50, 50, 20);
        ReadOnlyDoubleWrapper radiusWrapper = new ReadOnlyDoubleWrapper(50);
        radiusWrapper.set(100);
        ReadOnlyDoubleProperty radiusProperty = radiusWrapper.getReadOnlyProperty();
        circle.radiusProperty().bind(radiusProperty);
        group.getChildren().addAll(rect1, rect2, circle);
        Scene scene = new Scene(group, 600,400);
        stage.setScene(scene);
        stage.show();
    }
}
