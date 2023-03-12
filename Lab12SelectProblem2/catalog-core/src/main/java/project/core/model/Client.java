package project.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@NamedEntityGraphs({
        @NamedEntityGraph(name = "clientWithPhotoshoots",
                attributeNodes = @NamedAttributeNode(value = "photoshoots")),

        @NamedEntityGraph(name = "clientWithPhotoshootsAndLocation",
                attributeNodes = @NamedAttributeNode(value = "photoshoots",
                        subgraph = "photoshootWithLocation"),
                subgraphs = @NamedSubgraph(name = "photoshootWithLocation",
                        attributeNodes = @NamedAttributeNode(value = "location")))
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Client extends BaseEntity<Long>{
    private String name;
    private String email;
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "client_photoshoots",
            joinColumns = {
                @JoinColumn(
                    name = "client_id",
                    referencedColumnName = "id"
                )
            },
            inverseJoinColumns = {
                @JoinColumn(
                        name = "photoshoot_id",
                        referencedColumnName = "id"
                )
            }
    )
    @Builder.Default
    private Set<Photoshoot> photoshoots = new HashSet<>();

    public void addPhotoshoot(Photoshoot photoshoot){
        this.photoshoots.add(photoshoot);
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return name.equals(client.name) && email.equals(client.email) && phoneNumber.equals(client.phoneNumber);

    }


    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                "} " + super.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber);
    }
}
