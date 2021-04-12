package pl.wsb.exercises.collections;

import java.util.*;

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
        namesA.add("ALA");
        ((List<String>) namesA).sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length()) == 0 ? o1.compareTo(o2) : Integer.compare(o1.length(), o2.length());
            }
        });
        System.out.println(namesA);
    }
}
