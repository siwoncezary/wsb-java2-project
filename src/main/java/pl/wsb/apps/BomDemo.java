package pl.wsb.apps;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
public class BomDemo {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get("path-to-file");
        Files.write(file,new byte[]{(byte) 0xef, (byte) 0xbb, (byte) 0xbf}, StandardOpenOption.CREATE_NEW);
        Files.writeString(file,"ĄŻŹĆŃÓŁ;ĄŻŹĆÓŁ\nążźęćśńłó;ĄŻŚŹĆŃŁÓĘ", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }
}
