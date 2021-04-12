package pl.wsb.exercises.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        Collection<String> namesA = new ArrayList<>();
        Collection<String> namesL = new LinkedList<>();
        namesA.add("ADAM");
        namesA.add("EWA");
        namesA.add("KAROL");
        namesL.addAll(namesA);
        namesL.add("PAWEL");
        System.out.println(namesA);
        System.out.println(namesL);
        for(String item: namesL){
            System.out.println(item);
        }
        List<String> listNames = (List<String>) namesA;
        System.out.println(listNames.get(2));
        System.out.println(namesA.remove("KAROL"));
        System.out.println(listNames.contains("EWA"));
        System.out.println(listNames.indexOf("EWA"));
    }
}
