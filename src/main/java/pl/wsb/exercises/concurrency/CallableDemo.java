package pl.wsb.exercises.concurrency;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Callable<String> task = () -> {
            try {
                Thread.sleep(1000);
                return "Hello";
            } catch (InterruptedException e) {
                return "";
            }
        };
        Future<String> stringFuture = service.submit(task);
        try {
            System.out.println(stringFuture.get(500, TimeUnit.MILLISECONDS));
        } catch (TimeoutException e){
            System.out.println("Usługa nie została wykonana w zadanym czasie!");
        }
    }
}
