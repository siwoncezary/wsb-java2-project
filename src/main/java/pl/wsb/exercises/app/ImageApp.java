package pl.wsb.exercises.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(600, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image image = new Image("file:///c:\\data\\java-historia.png");
        gc.setFill(Color.BROWN);
        gc.setStroke(Color.BLUEVIOLET);
        gc.rect(10, 10, 200, 200);
        gc.drawImage(image, 0,0, 400, image.getHeight() * 400 / image.getWidth());
        WritableImage brighterImage = new WritableImage((int)image.getWidth(), (int)image.getHeight());
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = brighterImage.getPixelWriter();
        for (int x = 0; x < image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++){
                if (y > 50 & y <100) {
                    //writer.setColor(x, y, reader.getColor(x, y));
                    writer.setColor(x, y, Color.CHOCOLATE);
                }
            }
        }
        gc.drawImage(brighterImage,0, 400, 400, image.getHeight() * 400 / image.getWidth());
        Group root = new Group(canvas);
        Scene scene = new Scene(root, 600, 800);
        stage.setScene(scene);
        stage.show();
    }
}
