package lekton.deniill.services;

import java.security.InvalidParameterException;
import lekton.deniill.models.DepositModel;

public class DepositServiceImpl implements DepositService {

    @Override
    public int calculatePayback(DepositModel depositModel) {
        checkDepositParams(depositModel);

        double targetAmount = depositModel.getAmount() * depositModel.getMultiplier();
        double currentAmount = depositModel.getAmount();
        int years = 0;

        while (currentAmount < targetAmount) {
            currentAmount *= (1 + depositModel.getPercentage() / 100);
            years++;
        }

        return years;
    }

    private void checkDepositParams(DepositModel depositModel) {
        if (depositModel.getAmount() <= 0) {
            throw new InvalidParameterException("Сумма вклада должна быть положительным числом.");
        }

        if (depositModel.getPercentage() <= 0) {
            throw new InvalidParameterException(
                    "Процентная ставка должна быть положительным числом.");
        }

        if (depositModel.getMultiplier() <= 1) {
            throw new InvalidParameterException("Множитель должен быть больше 1.");
        }
    }
}
