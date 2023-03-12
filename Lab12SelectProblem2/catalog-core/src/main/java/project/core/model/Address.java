package project.core.model;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Address {
    private String city;
    private String street;
    private String number;
    private String zipCode;
    private String country;

}
