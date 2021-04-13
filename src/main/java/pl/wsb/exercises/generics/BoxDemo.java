package pl.wsb.exercises.generics;

import pl.wsb.app.model.City;

import java.time.LocalDate;

public class BoxDemo {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>("Tekst");
        Box<LocalDate> dateBox = new Box<>(LocalDate.now());
        Box<Integer> integerBox = new Box<>(null);
        System.out.println(stringBox.getContent());
        System.out.println(dateBox.getContent());
        System.out.println(integerBox.getContent());
        Box<Number> numberBox = new Box<>(null);
        numberBox.setContent(23.5f);
        Box<? extends Pizza> universalBox = new Box<>(new Pizza("dowolna"));
        Pizza content1 = (Pizza) universalBox.getContent();
        System.out.println(content1);
        Box objectBox = new Box(4);
        Integer content = (Integer) objectBox.getContent();
        System.out.println(content);
        //Box<City> cityBox = new Box<>(() -> new City(2, "AA","PL",124));
    }
}
