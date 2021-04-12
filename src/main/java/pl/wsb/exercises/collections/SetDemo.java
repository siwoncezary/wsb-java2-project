package pl.wsb.exercises.collections;

import java.util.*;

public class SetDemo {
    public static void main(String[] args) {
        Set<String> nameSet = new HashSet<>(List.of("ADAM", "KAROL","EWA"));
        System.out.println(nameSet.contains("ADAM"));
        nameSet.add("BEATA");
        System.out.println(nameSet);
        Set<String> treeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        treeSet.addAll(nameSet);
        System.out.println(treeSet);
    }
}
