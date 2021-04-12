package pl.wsb.exercises.generics;

public class Box<T> {
    private final T content;

    public Box(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public boolean isEmpty(){
        return content == null;
    }
}
