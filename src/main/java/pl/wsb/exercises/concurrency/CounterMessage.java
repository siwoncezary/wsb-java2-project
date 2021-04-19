package pl.wsb.exercises.concurrency;

public class CounterMessage implements Runnable{
    int[] globalCounter;

    public CounterMessage(int[] globalCounter){
        this.globalCounter = globalCounter;
    }
    @Override
    public void run() {
        int localCounter = 10000;
        while(localCounter-- > 0){
            synchronized (globalCounter) {
                globalCounter[0]++;
            }
        }
    }
}
