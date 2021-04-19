package pl.wsb.exercises.concurrency;

public class MessageThreadInterrupted extends Thread{
    @Override
    public void run() {
        while(!isInterrupted()){
            try {
                Thread.sleep(1000);
                System.out.println("Message from thread!");
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted!");
                //break lub return - koniec w wątku ale bez informacju zę został przerwany
                this.interrupt();
            }
        }
        System.out.println("End of thread!");
    }
}
