package pl.wsb.apps;

import java.util.stream.IntStream;

public class ByteNegationDemo {
    public static void main(String[] args) {
        short b = 0b01011010;
        short nb = (short) ~b;
        System.out.println(b);
        System.out.println(nb);
        System.out.println( Integer.toBinaryString(b)+" " + Integer.toBinaryString(nb & 0x000000FF));
        IntStream.range(244, 300).forEach(e -> System.out.println(Integer.toBinaryString(e & 0x000000FF)));
    }
}
