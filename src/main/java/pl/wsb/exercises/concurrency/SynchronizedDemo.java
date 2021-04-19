package pl.wsb.exercises.concurrency;

public class SynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
        int[] globalCounter = {0};
        CounterMessage user1Counter = new CounterMessage(globalCounter);
        CounterMessage user2Counter = new CounterMessage(globalCounter);
        Thread user1Thread = new Thread(user1Counter);
        Thread user2Thread = new Thread(user2Counter);
        user1Thread.start();
        user2Thread.start();
        user1Thread.join();
        user2Thread.join();
        System.out.println("KONIEC");
        System.out.println(globalCounter[0]);

    }
}
