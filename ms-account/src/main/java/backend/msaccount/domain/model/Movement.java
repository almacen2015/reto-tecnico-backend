package backend.msaccount.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movement {
    private Long id;
    private LocalDateTime date;
    private String type;
    private Double amount;
    private Double balance;
    private Long accountId;
}
