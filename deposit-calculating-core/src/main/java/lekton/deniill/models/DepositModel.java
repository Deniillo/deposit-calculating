package lekton.deniill.models;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SuperModel {
    private BigDecimal amount;
    private BigDecimal percentage;
    private BigDecimal multiplier;
}