package pl.wsb.app.model;

public class City {
    private final int id;
    private final String name;
    private final String countryCode;
    private final int population;

    public City(int id, String name, String countryCode, int population) {
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
    @Override
    public String toString(){
        return id + " " + name;
    }
}
