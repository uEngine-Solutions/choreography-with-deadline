package choreography.deadline.domain;

import choreography.deadline.domain.*;
import choreography.deadline.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String currencyId;
    private Double amount;
}
