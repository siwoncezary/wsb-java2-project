package pl.wsb.apps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
public class User {
    public String name;
    public LocalDate birthDate;
    public int points;

    public User(String name, LocalDate birthDate, int points) {
        this.name = name;
        this.birthDate = birthDate;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getPoints() {
        return points;
    }
}
