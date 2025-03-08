package lekton.deniill.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.security.InvalidParameterException;
import lekton.deniill.models.DepositModel;
import org.junit.jupiter.api.Test;

class DepositServiceImplTest {

    private final DepositService depositService = new DepositServiceImpl();

    @Test
    void calculatePayback_shouldReturnCorrectYears() {
        // Arrange
        double amount = 1000.0;
        double percentage = 5.0;
        double multiplier = 2.0;

        DepositModel depositModel =
                DepositModel.builder()
                        .amount(amount)
                        .percentage(percentage)
                        .multiplier(multiplier)
                        .build();

        // Act

        int years = depositService.calculatePayback(depositModel);

        // Assert
        int answer = 15;
        assertThat(years).isEqualTo(answer);
    }

    @Test
    void calculatePayback_shouldThrowException_ifAmountIsZeroOrNegative() {
        // Arrange
        double amount = -1000.0;
        double percentage = 5.0;
        double multiplier = 2.0;

        DepositModel depositModel =
                DepositModel.builder()
                        .amount(amount)
                        .percentage(percentage)
                        .multiplier(multiplier)
                        .build();

        // Act & Assert
        assertThatThrownBy(() -> depositService.calculatePayback(depositModel))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessage("Сумма вклада должна быть положительным числом.");
    }

    @Test
    void calculatePayback_shouldThrowException_ifPercentageIsZeroOrNegative() {
        // Arrange
        double amount = 1000.0;
        double percentage = -5.0;
        double multiplier = 2.0;

        DepositModel depositModel =
                DepositModel.builder()
                        .amount(amount)
                        .percentage(percentage)
                        .multiplier(multiplier)
                        .build();

        // Act & Assert
        assertThatThrownBy(() -> depositService.calculatePayback(depositModel))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessage("Процентная ставка должна быть положительным числом.");
    }

    @Test
    void calculatePayback_shouldThrowException_ifMultiplierIsLessThanOrEqualToOne() {
        // Arrange
        double amount = 1000.0;
        double percentage = 5.0;
        double multiplier = 1.0;

        DepositModel depositModel =
                DepositModel.builder()
                        .amount(amount)
                        .percentage(percentage)
                        .multiplier(multiplier)
                        .build();

        // Act & Assert
        assertThatThrownBy(() -> depositService.calculatePayback(depositModel))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessage("Множитель должен быть больше 1.");
    }
}
