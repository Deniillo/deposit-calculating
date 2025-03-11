package lekton.deniill.handlers;

import java.math.BigDecimal;
import java.util.Map;
import lekton.deniill.exceptions.InvalidDepositParamException;
import lekton.deniill.exceptions.InvalidFileFormatException;
import lekton.deniill.exceptions.MissingParamsException;
import lekton.deniill.models.DepositModel;
import lekton.deniill.services.DepositService;
import lekton.deniill.tools.FileParamsReader;
import lekton.deniill.tools.ParamsProvider;
import picocli.CommandLine;

@CommandLine.Command(
        name = "deposit",
        mixinStandardHelpOptions = true,
        description = "Управление вкладами")
public class DepositActions implements Runnable {

    private final DepositService depositService;

    public DepositActions(DepositService depositService) {
        this.depositService = depositService;
    }

    @CommandLine.Command(
            name = "calculate-payback",
            description =
                    "Высчитывает через сколько лет окупится ваш вклад и во сколько(c округлением"
                            + " вверх)")
    void calculatePayback(
            @CommandLine.Option(
                            names = {"-s", "--sum"},
                            description =
                                    "Сумма вашего вклада. Необходимо ввести любое дробное число без"
                                            + " букв")
                    BigDecimal sum,
            @CommandLine.Option(
                            names = {"-p", "--percentage"},
                            description =
                                    "Процентная ставка вашего вклада. Необходимо ввести любое"
                                            + " дробное число без букв")
                    BigDecimal percentage,
            @CommandLine.Option(
                            names = {"-m", "--multiplier"},
                            description =
                                    "Во сколько раз вклад должен увеличиться без внесения"
                                            + " дополнительных сумм и снимания денег со счета")
                    BigDecimal multiplier,
            @CommandLine.Option(
                            names = {"-f", "--file"},
                            description =
                                    "(Опциональный ввод) Абсолютный путь к файлу с параметрами")
                    String filePath) {
        try {
            if (filePath != null) {
                ParamsProvider paramsProvider = new FileParamsReader(filePath);
                Map<String, Double> params = paramsProvider.getParams();

                sum = BigDecimal.valueOf(params.get("sum"));
                percentage = BigDecimal.valueOf(params.get("percentage"));
                multiplier = BigDecimal.valueOf(params.get("multiplier"));
            }

            if (sum == null || percentage == null || multiplier == null) {
                throw new MissingParamsException(
                        "Необходимо указать все параметры либо через флаги, либо через файл."
                                + " --help");
            }

            DepositModel depositModel =
                    DepositModel.builder()
                            .amount(sum)
                            .percentage(percentage)
                            .multiplier(multiplier)
                            .build();

            int years = depositService.calculatePayback(depositModel);

            String message =
                    String.format(
                            "Сумма вклада увеличится в %.2f раза через %d лет", multiplier, years);

            System.out.println(message);

        } catch (InvalidFileFormatException
                | MissingParamsException
                | InvalidDepositParamException e) {

            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public void run() {}
}
