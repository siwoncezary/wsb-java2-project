package pl.wsb.exercises.regexp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpDemo {
    public static void main(String[] args) {
        //wzorzec kodu pocztowego
        Pattern pattern = Pattern.compile("\\d{2}-\\d{3}");
        //wczytanie dokładnie dwóch słów
        //Pattern pattern = Pattern.compile("\\w+\\s+\\w+");
        //wzorzec liczby mniejszej lub równej od 5
        //Pattern pattern = Pattern.compile("^[0-5]$");
        //wzorzec liczby mniejszej lub równej od 15
        //Pattern pattern = Pattern.compile("[0-5]|[1][0-5]");
        //Przykładowy wzorzec adresu email
        //Pattern pattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9.]+[a-z]@[a-zA-Z.]{2,}.[a-z]{2,3}");
        //Przykładowy ściezki do pliku
        //Pattern pattern = Pattern.compile("katalog\\\\[a-z]+");
        Scanner scanner = new Scanner(System.in);
        Matcher matcher = pattern.matcher(scanner.nextLine().trim());
        System.out.println(matcher.matches());
    }
}
