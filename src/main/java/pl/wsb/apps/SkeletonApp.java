package pl.wsb.apps;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class SkeletonApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var node = new Group();
//        node.minHeight(100);
//        node.minWidth(100);
//        node.prefHeight(100);
//        node.prefWidth(100);
//        node.maxHeight(100);
//        node.maxWidth(100);
//        node.intersects(new BoundingBox(0,0,10,10));
//        node.contains(new Point2D(100,100));
//        node.localToParent(new Point2D(100,100));
//        node.parentToLocal(new Point2D(100,100));
        var x1 = 100;
        var y1 = 100;
        var x2 = 200;
        var y2 = 200;
        var x3 = 300;
        var y3 = 300;
        var width = 200;
        var height = 100;
        var radius = 10;
        var line = new Line(x1,y1, x2, y2);
        var ellipse = new Ellipse(x1, y1, width, height);
        var circle = new Circle(x1, y1, width);
        var roundedRectangle = new Rectangle(x1, y1, width, height);
        roundedRectangle.setArcHeight(radius);
        roundedRectangle.setArcWidth(radius);
        var rectangle = new Rectangle(x1, y1, width, height);
        var controlX = 100;
        var controlY = 200;
        var polygon = new Polygon(
                100, 100,
                150, 150,
                200, 100);
        var triangle = new Path();
        triangle.getElements().addAll(
                new MoveTo(100,100),
                new LineTo(150 , 150),
                new LineTo(200,100),
                new LineTo(100,100)
        );
        QuadCurve quadcurve = new QuadCurve(x1, y1, controlX, controlY, x2, y2);
        LinearGradient gradient = new LinearGradient(
                0,
                0,
                width,
                0,
                false,
                CycleMethod.NO_CYCLE,
                new Stop(0f,Color.rgb(255,255, 255, 1)),
                new Stop(1f, Color.rgb(20,60,0,1))
                );
        rectangle.setFill(gradient);
        Color color = Color.BURLYWOOD;
        color = Color.rgb(123, 0, 45, 0.9);
        color = Color.web("#FFAA11");
        color = Color.hsb(120, 0.5, 1, 1);
        SimpleDoubleProperty widthProperty = new SimpleDoubleProperty(300);
        var rect1 = new Rectangle(100,100, 200, 100);
        var rect2 = new Rectangle( 200, 300, 200, 100);
        rect1.widthProperty().bindBidirectional(widthProperty);
        rect2.widthProperty().bindBidirectional(widthProperty);
        widthProperty.set(200);

        DoubleBinding binding = new DoubleBinding() {
            {
                super.bind(stage.heightProperty());
            }
            @Override
            protected double computeValue() {
                return stage.heightProperty().get() - 500;
            }
        };
        rect2.heightProperty().bind(stage.heightProperty().subtract(500));
        rect2.heightProperty().bind(binding);

        ReadOnlyStringWrapper nameWrapper = new ReadOnlyStringWrapper("XYZ");
        ReadOnlyStringProperty nameProperty = nameWrapper.getReadOnlyProperty();
        var nameText = new Text();
        nameText.setX(10);
        nameText.setY(40);
        nameText.textProperty().bind(nameProperty);

        //rectangle.setRotate(45);
        var text = new Text(x1, y1, "Text");
        Font serif = Font.font("Lato Black", 30);
        text.setFont(serif);
        text.setFill(Color.RED);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0f);
        dropShadow.setOffsetY(2.0f);
        dropShadow.setColor(Color.rgb(50, 50, 50, .588));
        text.setEffect(dropShadow);
        node.getChildren().addAll(rect1, rect2, nameText);
        Font.getFamilies().forEach(System.out::println);
        var scene = new Scene(node, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
