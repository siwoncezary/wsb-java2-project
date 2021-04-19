package pl.wsb.exercises.concurrency;

public class MessageThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        MessageThread thread = new MessageThread();
        System.out.println(thread.getState());
        thread.setDaemon(true);
        thread.start();
        System.out.println(thread.getState());
        System.out.println(thread.isAlive());
        Thread.sleep(4000);
        System.out.println("KONIEC MAIN");
    }
}
