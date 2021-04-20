package pl.wsb.exercises.app;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;

import java.util.Random;

public class BallAnimationThread implements Runnable {
    static Random random = new Random();
    private final Scene root;
    private final Circle ball;
    double dx, dy;
    Thread thread;

    public BallAnimationThread(Scene root, Circle ball) {
        this.root = root;
        this.ball = ball;
        dx = 2 + random.nextInt(5);
        dy = 2 + random.nextInt(5);
        dx = random.nextBoolean() ? -dx : dx;
        dy = random.nextBoolean() ? -dy : dy;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!thread.isInterrupted()) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                thread.interrupt();
            }
            var minX = 0;
            var minY = 0;
            var maxX = root.getWidth();
            var maxY = root.getHeight();
            if (minX >= ball.getCenterX() - ball.getRadius() + 1 || maxX <= ball.getCenterX() + ball.getRadius() + 1) {
                dx = -dx;
            }
            if (minY >= ball.getCenterY() - ball.getRadius() + 1 || maxY <= ball.getCenterY() + ball.getRadius() + 1) {
                dy = -dy;
            }
            Platform.runLater(() -> {
                ball.setCenterX(ball.getCenterX() + dx);
                ball.setCenterY(ball.getCenterY() + dy);
            });
        }
    }

    public Thread getThread() {
        return thread;
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
