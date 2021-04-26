package pl.wsb.exercises.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MediaApp extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Media mp3 = new Media("file:///c:/data/hero.mp3");
        MediaPlayer player = new MediaPlayer(mp3);
        player.play();
        Text playTime = new Text(0, 20, "Play time");
        playTime.setFont(Font.font("Arial", 20));
        root.getChildren().addAll(playTime);
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> playTime.setText("Play time: " + player.getCurrentTime().toSeconds()), 0, 1, TimeUnit.SECONDS);
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}
