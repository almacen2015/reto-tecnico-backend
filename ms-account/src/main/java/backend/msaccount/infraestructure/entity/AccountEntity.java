package backend.msaccount.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double initialBalance;

    @Column(nullable = false)
    private Boolean status;

    @Column(name = "client_id", nullable = false)
    private Long clientId;
}
