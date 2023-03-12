package project.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
//@Data
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@Getter
@Setter
public class Location extends BaseEntity<Long>{
    private String name;
    private float entranceFee;
    private String owner;
    private int timeLimit;

    @Embedded
    private Address address;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    //@JsonIgnore
    //private Set<Photoshoot> photoshoots;
}
