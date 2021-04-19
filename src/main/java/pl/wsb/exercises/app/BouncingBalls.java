package pl.wsb.exercises.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BouncingBalls extends Application {
    List<Circle> balls;
    ExecutorService service = Executors.newFixedThreadPool(10);
    List<Thread> threads;
    Group root;
    @Override
    public void start(Stage stage) throws Exception {
        root = prepareRoot(stage);
        balls = prepareBalls(10);
        root.getChildren().addAll(balls);
        List<Thread> threads = runAnimation(balls);
    }

    public void stopAnimation(){
        threads.stream().forEach(t -> t.interrupt());
    }

    private List<Thread> runAnimation(List<Circle> balls){
        var threads = new ArrayList<Thread>();
        for (Circle ball: balls){
            threads.add(new Thread(new BallAnimationThread(root, ball)));
            System.out.println(ball);
        }
        for(Thread thread : threads){
            thread.start();
            System.out.println(thread);
        }
        return threads;
    }

    private List<Circle> prepareBalls(int size){
        Random random = new Random();
        return IntStream.range(0, size)
                .mapToObj(i -> new Circle(random.nextInt(400), random.nextInt(400), 20 + random.nextInt(20), Color.BEIGE))
                .collect(Collectors.toList());
    }
    private Group prepareRoot(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Bouncing balls");
        stage.show();
        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}
