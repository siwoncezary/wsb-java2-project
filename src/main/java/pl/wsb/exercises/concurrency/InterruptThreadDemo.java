package pl.wsb.exercises.concurrency;

import java.util.Scanner;

public class InterruptThreadDemo {
    public static void main(String[] args) {
        MessageThreadInterrupted thread = new MessageThreadInterrupted();
        thread.start();
        Scanner scanner = new Scanner(System.in);
        if (scanner.next().equals("Q")){
            thread.interrupt();
        }
    }
}
