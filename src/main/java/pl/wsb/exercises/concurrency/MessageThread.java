package pl.wsb.exercises.concurrency;

public class MessageThread extends Thread{
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Message from thread!");
        }
    }
}
