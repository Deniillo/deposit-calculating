package lekton.deniill.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import lekton.deniill.models.DepositModel;

public class DepositServiceImpl implements DepositService {

    @Override
    public int calculatePayback(DepositModel depositModel) {
        checkDepositParams(depositModel);

        BigDecimal targetAmount = depositModel.getAmount()
                .multiply(depositModel.getMultiplier());
        BigDecimal currentAmount = depositModel.getAmount();
        BigDecimal percentage = BigDecimal.ONE.add(
                depositModel.getPercentage()
                        .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
        );
        int years = 0;

        while (currentAmount.compareTo(targetAmount) < 0) {
            currentAmount = currentAmount
                    .multiply(percentage);
            years++;
        }

        return years;
    }

    private void checkDepositParams(DepositModel depositModel) {
        if (depositModel.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidParameterException("Сумма вклада должна быть положительным числом.");
        }

        if (depositModel.getPercentage().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidParameterException(
                    "Процентная ставка должна быть положительным числом.");
        }

        if (depositModel.getMultiplier().compareTo(BigDecimal.ONE) <= 0) {
            throw new InvalidParameterException("Множитель должен быть больше 1.");
        }
    }
}
