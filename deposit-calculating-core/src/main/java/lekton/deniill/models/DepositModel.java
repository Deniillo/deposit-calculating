package lekton.deniill.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DepositModel {
    private double amount;
    private double percentage;
    private double multiplier;
}
