package pl.wsb.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private  int id;
    private  String name;
    private  String countryCode;
    private  int population;
    private  double latitude;
    private  double longitude;
    private  int elevation;
    private  String timezone;
    public City(int id, String name, String countryCode, int population){
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getPopulation() {
        return population;
    }
}
