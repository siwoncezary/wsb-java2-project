package pl.wsb.apps;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class ImageApp extends Application {
    Logger logger = Logger.getLogger(ImageApp.class.getName());
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Image image = new Image("https://ss7.vzw.com/is/image/VerizonWireless/iphone6s-plus-gld-front?$png8alpha256$&hei=410");
        System.out.println(image.getProgress());
        logger.info("Image size " + image.getWidth() + " x " + image.getHeight());
        WritableImage target = new WritableImage((int)image.getWidth(),(int) image.getHeight());
        PixelWriter pixelWriter = target.getPixelWriter();
        PixelReader pixelReader = image.getPixelReader();
        for(int x = 0; x < image.getWidth(); x++){
            for (int y = 0; y < image.getHeight(); y++){
                Color color = pixelReader.getColor(x, y);
                color = color.brighter();
                pixelWriter.setColor(x , y, color);
            }
        }
        Canvas canvas = new Canvas(image.getWidth(), image.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(target, 10,10);
        root.getChildren().add(canvas);

        var scene = new Scene(root, 400, 600);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
