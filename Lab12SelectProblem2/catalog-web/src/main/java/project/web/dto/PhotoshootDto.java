package project.web.dto;

import lombok.*;
import project.core.model.Location;

import java.sql.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PhotoshootDto extends BaseDto{
    private int price;
    private String date;
    private int NoMaxClients;
    private Location location;
    private Set<Long> clients;

    @Override
    public String toString() {
        return "PhotoshootDto{" +
                "price=" + price +
                ", date=" + date +
                ", NoMaxClients=" + NoMaxClients +
                ", location=" + location +
                "} " + super.toString();
    }
}
