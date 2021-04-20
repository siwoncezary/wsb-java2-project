package pl.wsb.exercises.app;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BouncingBalls extends Application {
    List<Circle> balls;
    ExecutorService service = Executors.newFixedThreadPool(10);
    ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
    SimpleStringProperty pointsProperty = new SimpleStringProperty("Liczba punktów: 0");
    SimpleStringProperty timeProperty = new SimpleStringProperty("Czas do końca gry: ");
    int points;
    AtomicInteger gameTime = new AtomicInteger(20);
    Group root;

    @Override
    public void start(Stage stage) throws Exception {
        root = prepareRoot(stage);
        balls = prepareBalls(10);
        root.getChildren().addAll(balls);
        runAnimation(balls);
        startTimer();
    }

    private void startTimer(){
        timer.scheduleAtFixedRate(() -> {
           int time = gameTime.decrementAndGet();
           timeProperty.set("Czas do końca gry: " + time);
           if (time == 0){
               stopAnimation();
               stopTimer();
               stopBallClicked(balls);
           }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    private void stopTimer(){
        timer.shutdownNow();
    }

    public void stopAnimation() {
        service.shutdownNow();
    }

    private void stopBallClicked(List<Circle> balls){
        balls.forEach(ball -> ball.setOnMouseClicked(e ->{}));
    }

    private void runAnimation(List<Circle> balls) {
        for (Circle ball : balls) {
            service.execute(new BallAnimationThread(root.getScene(), ball));
        }
    }

    private List<Circle> prepareBalls(int size) {
        Random random = new Random();
        return IntStream.range(0, size)
                .mapToObj(i -> new Circle(50 + random.nextInt(200), 50 + random.nextInt(200), 20 + random.nextInt(30), Color.BEIGE))
                .map(ball -> {
                            ball.setOnMouseClicked(e -> {
                                points++;
                                pointsProperty.set("Liczba punktów: " + points);
                            });
                            return ball;
                        }
                )
                .collect(Collectors.toList());
    }

    private Group prepareRoot(Stage stage) {
        Group root = new Group();
        Text points = new Text(10, 50, "Liczba punktów: 0");
        Text time = new Text(10, 100,"");
        time.setFill(Color.RED);
        time.setFont(Font.font("Arial", 18));
        time.textProperty().bindBidirectional(timeProperty);
        points.setFill(Color.BROWN);
        Font font = Font.font("Arial Black", 20);
        points.setFont(font);
        points.textProperty().bindBidirectional(pointsProperty);
        root.getChildren().addAll(points, time);
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Bouncing balls");
        stage.show();
        stage.setOnCloseRequest(e -> {
            stopAnimation();
        });
        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}
