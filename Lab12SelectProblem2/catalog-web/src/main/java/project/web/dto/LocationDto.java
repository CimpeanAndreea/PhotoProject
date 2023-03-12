package project.web.dto;

import lombok.*;
import project.core.model.Address;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class LocationDto extends BaseDto{
    private String name;
    private float entranceFee;
    private String owner;
    private int timeLimit;
    private Address address;
}
