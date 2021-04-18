package pl.wsb.exercises.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class NewIoDemo {
    public static void main(String[] args) throws IOException {
        Path citiesPath = Paths.get("c:/data/cities500.txt");
        Path currentDirectory = Paths.get("./test/../../app");
        Path acme = Paths.get("./test/plik.txt");
        System.out.println(currentDirectory.normalize());
        System.out.println(currentDirectory.resolve(acme).normalize());
        System.out.println(Files.exists(citiesPath));
        System.out.println(Files.isDirectory(citiesPath));
        System.out.println(Files.isExecutable(citiesPath));
        //Kopiowanie pliku
        //Files.copy(citiesPath,Paths.get("c:/data/cities_copy.txt"));
        //Wczytanie całego pliku do kolekcji
        List<String> strings = Files.readAllLines(citiesPath);
    }
}
