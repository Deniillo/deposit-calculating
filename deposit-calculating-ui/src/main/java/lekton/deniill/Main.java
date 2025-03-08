package lekton.deniill;

import lekton.deniill.handlers.MainMenu;
import lekton.deniill.services.DepositServiceImpl;

public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu(new DepositServiceImpl());
        mainMenu.run();
    }
}
