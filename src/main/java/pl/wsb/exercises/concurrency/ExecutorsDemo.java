package pl.wsb.exercises.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            int i = 10;
            while(i-- > 0){
                System.out.println(Thread.currentThread().getId());
            }
        });
        service.execute(() -> {
            int i = 10;
            while(i-- > 0){
                System.out.println(Thread.currentThread().getId());
            }
        });
        service.execute(() -> {
            int i = 10;
            while(i-- > 0){
                System.out.println(Thread.currentThread().getId());
            }
        });
        Thread.sleep(100);
        service.execute(() -> System.out.println("Last thread! " + Thread.currentThread().getId()));
        service.shutdown();
        ScheduledExecutorService service1 = Executors.newScheduledThreadPool(2);
        service1.schedule(() -> System.out.println("DELAYED"), 1000, TimeUnit.MILLISECONDS);
        service1.scheduleAtFixedRate(() -> System.out.println("FIXED RATE"), 0, 2000, TimeUnit.MILLISECONDS);
        service1.scheduleWithFixedDelay(() -> System.out.println("FIXED DELAY"), 0, 1000, TimeUnit.MILLISECONDS);
        Thread.sleep(10000);
        service1.shutdown();
    }
}
