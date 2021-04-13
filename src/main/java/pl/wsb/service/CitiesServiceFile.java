package pl.wsb.service;

import pl.wsb.app.model.City;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CitiesServiceFile implements CitiesService {
    private final List<City> cities;

    public CitiesServiceFile(String path) {
        cities = new ArrayList<>();
        try (var fileReader = new FileReader(path);
             var reader = new BufferedReader(fileReader)
        ) {
            String line;
            while((line = reader.readLine()) != null){
                String[] columns = line.split("\\t");
                int id = Integer.parseInt(columns[0]);
                String name = columns[1];
                double latitude = Double.parseDouble(columns[4]);
                double longitude = Double.parseDouble(columns[5]);
                String countryCode = columns[8];
                int population = Integer.parseInt(columns[14]);
                int elevation = 0;
                if (!columns[15].isEmpty()) {
                    elevation = Integer.parseInt(columns[15]);
                }
                City city = City.builder()
                        .id(id)
                        .name(name)
                        .latitude(latitude)
                        .longitude(longitude)
                        .countryCode(countryCode)
                        .population(population)
                        .elevation(elevation)
                        .build();
                cities.add(city);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<City> findAll() {
        return cities;
    }
}
