package pl.wsb.exercises.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedMethodDemo {
    public static void main(String[] args) throws InterruptedException {
        final GlobalCounter globalCounter = new GlobalCounter();
        AtomicInteger integerCounter = new AtomicInteger(0);
        Thread user1 = new Thread(() -> {
           int counter = 1000;
           while(counter-- > 0){
               globalCounter.inc();
               integerCounter.getAndIncrement();
           }
        });
        Thread user2 = new Thread(() -> {
            int counter = 1000;
            while(counter-- > 0){
                globalCounter.inc();
                integerCounter.getAndIncrement();
            }
        });
        user1.start();
        user2.start();
        user1.join(); //metoda main czeka na zakończenie wątku user1
        user2.join(); //metoda main czeka na zakończenie wątku user2
        System.out.println(globalCounter.get());
        System.out.println(integerCounter.get());
    }
}
