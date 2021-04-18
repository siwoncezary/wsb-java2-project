package pl.wsb.exercises.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpGroupDemo {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(\\d{2}-\\d{3})");
        String str = "sdkkja alkj lkfj 45-578 fdsd sdfs fdsfa s 12-567  dsdfs";
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
