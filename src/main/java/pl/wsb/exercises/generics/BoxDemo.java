package pl.wsb.exercises.generics;

import java.time.LocalDate;

public class BoxDemo {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>("Tekst");
        Box<LocalDate> dateBox = new Box<>(LocalDate.now());
        Box<Integer> integerBox = new Box<>(null);

    }
}
