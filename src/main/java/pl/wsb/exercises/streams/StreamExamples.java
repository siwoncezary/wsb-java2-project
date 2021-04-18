package pl.wsb.exercises.streams;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
class City {
    int id;
    String name;
    double latitude;
    double longitude;
    String countryCode;
    long population;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", countryCode='" + countryCode + '\'' +
                ", population=" + population +
                '}';
    }

    public City(int id, String name, double latitude, double longitude, String countryCode, long population) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.countryCode = countryCode;
        this.population = population;

    }
}
public class StreamExamples {
    public static void main(String[] args) throws IOException {
        Path citiesPath = Paths.get("c:/data/cities500.txt");
        List<String> strings = Files.readAllLines(citiesPath);
        //Wczytanie całego pliku do kolekcji obiektów
        List<City> cities = strings.stream()
                .map(line -> {
                    String[] columns = line.split("\\t");
                    return new City(
                            Integer.parseInt(columns[0]),
                            columns[1],
                            Double.parseDouble(columns[4]),
                            Double.parseDouble(columns[5]),
                            columns[8],
                            Long.parseLong(columns[14])
                    );
                })
                .collect(Collectors.toList());
        //Pierwsze miasto o populacji powyżej 10 000
        Optional<City> first = cities.
                parallelStream()
                .filter(city -> "PL".equals(city.countryCode))
                .filter(city -> city.population > 10_000)
                .peek(System.out::println)
                .findFirst();
        System.out.println(first);
        //Miasta
        //cities.stream().distinct().map(city -> city.name).sorted((a, b) -> a.length() - b.length()).skip(2000).limit(1000).forEach(System.out::println);
        List<String> acity = cities.stream().map(city -> city.name).sorted().takeWhile(name -> name.startsWith("'A")).collect(Collectors.toList());
        System.out.println(acity.size());
        System.out.println(acity);
        //flatMap na typ obiektowy
        Stream<Stream<Integer>> streamStream = Stream.of(Stream.of(1, 2, 4), Stream.of(5, 6, 9), Stream.of(8, 3));
        streamStream.flatMap(stream -> stream).filter(x -> x >2).forEach(System.out::println);
        //flatMap na typ prosty
        String[] strArr ={"ABCDEFG ASDFS FS", "afsdfsdf", "sdsfds"};
        Arrays.asList(strArr).stream().flatMapToInt(str -> str.chars()).distinct().mapToObj(s -> "" + (char)s).forEach(System.out::print);

        System.out.println(cities.stream().peek(System.out::println).anyMatch(city -> city.countryCode.equals("AU")));
        Optional<City> maxCity = cities.stream().max((c1, c2) -> (int) (c1.population - c2.population));
        Optional<City> minCity = cities.stream().min((c1, c2) -> Long.compare(c1.population, c2.population));
        System.out.println(maxCity);
        System.out.println(minCity);
        long sum = cities.stream().filter(city -> Objects.equals(city.countryCode, "PL")).mapToLong(city -> city.population).sum();
        System.out.println("Populacja miast polskich " + sum);
        long us = cities.stream()
                .filter(city -> Objects.equals(city.countryCode, "US"))
                .mapToLong(city -> city.population)
                .reduce(0, (acc, e) -> acc + e);
        String reduce = Arrays.asList("ARA", "BCD","DFG").stream().reduce("", (acc, e) -> acc + e);
        System.out.println(reduce);
        System.out.println("Populacja USA " + us);
        int reduce1 = IntStream.range(1,10).reduce(1, (acc, e) -> acc * e);
        System.out.println(reduce1);
        Map<String, List<City>> collect = cities.stream().collect(Collectors.groupingBy(city -> city.countryCode));
        System.out.println(collect.get("PL"));
        Map<String, LongSummaryStatistics> collect1 = cities.stream()
                .collect(Collectors.groupingBy(city -> city.countryCode, Collectors.summarizingLong(city -> city.population)));
        System.out.println(collect1.get("PL"));
        Map<Boolean, List<City>> collect2 = cities.stream()
                .filter(city -> Objects.equals(city.countryCode, "PL"))
                .collect(Collectors.partitioningBy(city -> city.population > 10_000));
        System.out.println(collect2.get(true));
    }
}
