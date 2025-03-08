package lekton.deniill.handlers;

import java.util.Scanner;
import lekton.deniill.services.DepositService;
import picocli.CommandLine;

public class MainMenu implements Runnable {

    private final DepositService depositService;

    public MainMenu(DepositService depositService) {
        this.depositService = depositService;
    }

    @Override
    public void run() {
        CommandLine commandLine = new CommandLine(new DepositActions(depositService));

        System.out.println("Привет! Ты в главном меню моей программы");
        System.out.println("Напиши --help для помощи или --quit для выхода из программы");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String line = scanner.nextLine();
                if (line.equals("--quit")) {
                    break;
                } else {
                    commandLine.execute(line.split(" "));
                }
            }
        }
    }
}
