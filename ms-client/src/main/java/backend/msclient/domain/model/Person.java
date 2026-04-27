package backend.msclient.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    private String name;
    private Gender gender;
    private String identification;
    private String address;
    private String phone;
}
