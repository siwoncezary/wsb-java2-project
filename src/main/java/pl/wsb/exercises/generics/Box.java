package pl.wsb.exercises.generics;

import java.util.function.Supplier;

public class Box<T> {
    private T content;

    public Box(T content) {
        this.content = content;
    }

    public Box(Supplier<T> supplier){
        this.content = supplier.get();
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isEmpty(){
        return content == null;
    }
}
