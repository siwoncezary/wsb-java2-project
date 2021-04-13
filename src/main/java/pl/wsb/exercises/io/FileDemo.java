package pl.wsb.exercises.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        InputStream input = new FileInputStream("c:\\data\\cities500.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(inputStreamReader);
//        var data = 0;
//        while((data = input.read()) > -1){
//            char character = (char) data;
//            System.out.print(character);
//        }
        String line;
        List<String> cities = new ArrayList<>();
        while((line = reader.readLine()) != null){
            cities.add(line);
            System.out.println(line);
        }
        input.close();
        OutputStream output = new FileOutputStream("c:\\data\\polish_cities.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
        PrintWriter writer = new PrintWriter(outputStreamWriter);
        for(String city: cities){
            String [] tokens = city.split("\\t");
            if (tokens[8].equals("PL")){
                writer.println(city);
            }
        }
        writer.close();
    }
}
