package pl.wsb.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.util.Arrays;

public class MediaApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var root = new VBox();
        var scene = new Scene(root, 400, 600);
        var info = new TextArea();
        var stop = new Button("STOP");
        var play = new Button("PLAY");
        Media media = new Media("file:///c:/data/hero.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        play.setOnAction(e -> mediaPlayer.play());
        root.getChildren().addAll(info, stop, play);
        var infoThread = new Thread(() ->{
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    info.clear();
                    info.appendText("STATUS: " + mediaPlayer.getStatus() + "\n");
                    info.appendText("DURATION: " + mediaPlayer.getTotalDuration() + "\n");
                    info.appendText("CURRENT: " + mediaPlayer.getCurrentTime() + "\n");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        mediaPlayer.setAudioSpectrumListener(new AudioSpectrumListener() {
            @Override
            public void spectrumDataUpdate(double timeStamp, double duration, float[] magnitudes, float[] phases) {
                System.out.println("SPECTRUM INFO: " + timeStamp  + " duration " + "\n" + Arrays.toString(magnitudes));
            }
        });
        infoThread.start();
        stop.setOnAction(e -> {
            mediaPlayer.stop();
        });
        stage.setOnCloseRequest(e -> infoThread.interrupt());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
