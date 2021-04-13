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
}
