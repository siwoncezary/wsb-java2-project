package pl.wsb.exercises.concurrency;

public class GlobalCounter {
    int counter;

    synchronized public void inc(){
        counter++;
    }

    public int get(){
        return counter;
    }
}
