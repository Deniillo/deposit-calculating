```
package lekton.deniill.models;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
public class DepositModel {
    private BigDecimal amount;
    private BigDecimal percentage;
    private BigDecimal multiplier;
}
```