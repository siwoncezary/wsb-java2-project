package pl.wsb.exercises.app;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.shape.Circle;

import java.util.Random;

public class BallAnimationThread implements Runnable{
    static Random random = new Random();
    private final Group root;
    private final Circle ball;
    double dx, dy;

    public BallAnimationThread(Group root, Circle ball) {
        this.root = root;
        this.ball = ball;
        dx = 1 + random.nextInt(5);
        dy = 1 + random.nextInt(5);
        dx = random.nextBoolean() ? -dx : dx;
        dy = random.nextBoolean() ? -dy : dy;
        System.out.println(toString());
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        while(!current.isInterrupted()){
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
               current.interrupt();
            }
            if (0 > ball.getCenterX() - ball.getRadius() + dx || 500 < ball.getCenterX() + ball.getRadius() + dx){
                dx = -dx;
            }
            if (0 > ball.getCenterY() - ball.getRadius() + dy || 500 < ball.getCenterY() + ball.getRadius() + dy){
                dy = -dy;
            }
            Platform.runLater(() -> {
                ball.setCenterX(ball.getCenterX() + dx);
                ball.setCenterY(ball.getCenterY() + dy);
            });
        }
    }

    @Override
    public String toString() {
        return "BallAnimationThread{" +
                "root=" + root +
                ", ball=" + ball +
                ", dx=" + dx +
                ", dy=" + dy +
                '}';
    }
}
