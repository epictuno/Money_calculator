package moneycalculator.view;

import moneycalculator.control.MCController;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;

public interface Dialog {
    public Money getMoney();
    public Currency getCurrencyTo();
    public void registerController(MCController mCController);
    public void refreshMoney(Money money);
}
