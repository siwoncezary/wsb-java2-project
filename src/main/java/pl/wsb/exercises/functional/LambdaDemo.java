package pl.wsb.exercises.functional;

import pl.wsb.app.model.City;

import java.util.function.*;

public class LambdaDemo {
    public static void main(String[] args) {
//        Predicate<City> overMillionPredicate = new Predicate<City>() {
//            @Override
//            public boolean test(City city) {
//                return city.getPopulation() > 1_000_000;
//            }
//        };
//        Predicate<City> overMillion = city -> city.getPopulation() > 1_000_000;
//        boolean test = overMillion.test(new City(2, "AAA", "PL", 34566));
//        System.out.println(test);
//        Consumer<City> print = city -> System.out.println(city);
//        BiConsumer<String, City> printWithPrefix = (prefix, city) -> System.out.println(prefix +" " + city);
//        print.accept(new City(2, "AAA", "PL", 3455));
//        printWithPrefix.accept("Miasto", new City(2, "AAA", "PL", 3455));
//        Function<Double, Integer> round = x -> Math.toIntExact(Math.round(x));
//        Function<String, Integer> length = str -> str.length();
//        System.out.println(round.apply(234.67));
//        System.out.println(length.apply("AAAAAAS"));
//        Supplier<String> defaultName = () -> "name";
    }
}
