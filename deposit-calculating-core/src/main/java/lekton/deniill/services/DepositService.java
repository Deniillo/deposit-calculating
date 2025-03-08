package lekton.deniill.services;

import lekton.deniill.models.DepositModel;

public interface DepositService {
    int calculatePayback(DepositModel depositModel);
}
