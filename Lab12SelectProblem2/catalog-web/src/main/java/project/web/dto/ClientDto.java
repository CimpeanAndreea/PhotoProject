package project.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@EqualsAndHashCode(callSuper = true)
public class ClientDto extends BaseDto {
    private String name;
    private String email;
    private String phoneNumber;

}
