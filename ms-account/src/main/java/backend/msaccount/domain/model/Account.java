package backend.msaccount.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    private Long id;
    private String number;
    private String type;
    private Double initialBalance;
    private Boolean status;
    private Long clientId;
}
