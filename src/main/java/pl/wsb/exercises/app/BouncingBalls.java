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
    Group root;
    @Override
    public void start(Stage stage) throws Exception {
        root = prepareRoot(stage);
        balls = prepareBalls(10);
        root.getChildren().addAll(balls);
        runAnimation(balls);
    }

    public void stopAnimation(){
        service.shutdownNow();
    }

    private void runAnimation(List<Circle> balls){
        for (Circle ball: balls){
            service.execute(new BallAnimationThread(root.getScene(), ball));
        }
    }

    private List<Circle> prepareBalls(int size){
        Random random = new Random();
        return IntStream.range(0, size)
                .mapToObj(i -> new Circle(50 + random.nextInt(200),50 + random.nextInt(200), 20 + random.nextInt(30), Color.BEIGE))
                .collect(Collectors.toList());
    }
    private Group prepareRoot(Stage stage){
        Group root = new Group();
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
