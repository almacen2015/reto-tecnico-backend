package backend.msclient.domain.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    private String name;
    private String gender;
    private String identification;
    private String address;
    private String phone;
}
