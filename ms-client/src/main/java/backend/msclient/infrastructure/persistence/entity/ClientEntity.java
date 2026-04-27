package backend.msclient.infrastructure.persistence.entity;

import backend.msclient.domain.model.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(nullable = false, unique = true)
    private String identification;

    private String address;
    private String phone;

    @Column(nullable = false)
    private String password;
    private Boolean status;
}
