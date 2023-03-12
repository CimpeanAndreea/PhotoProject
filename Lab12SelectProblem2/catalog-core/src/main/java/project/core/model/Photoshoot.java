package project.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "photoshootWithLocation",
                attributeNodes = @NamedAttributeNode(value = "location")),

        @NamedEntityGraph(name = "photoshootWithClientsAndLocation",
                attributeNodes = { @NamedAttributeNode(value = "clients"),
                                   @NamedAttributeNode(value = "location")})
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Photoshoot extends BaseEntity<Long> {
    private int price;

    private Date date;

    private int NoMaxClients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    //@ManyToOne(cascade = CascadeType.ALL)
    //@JsonIgnore
    private Location location;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "client_photoshoots",
            joinColumns = {
                    @JoinColumn(
                            name = "photoshoot_id",
                            referencedColumnName = "id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "client_id",
                            referencedColumnName = "id"
                    )
            }
    )
    @Builder.Default
    private Set<Client> clients = new HashSet<>();

    private void addClient(Client client){
        this.clients.add(client);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Photoshoot that = (Photoshoot) o;
        return price == that.price && NoMaxClients == that.NoMaxClients && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price, date, NoMaxClients);
    }

    @Override
    public String toString() {
        return "Photoshoot{" +
                "price=" + price +
                ", date=" + date +
                ", NoMaxClients=" + NoMaxClients +
                "} " + super.toString();
    }
}
