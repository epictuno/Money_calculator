package moneycalculator.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.constant.ConstantDescs.NULL;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;
import moneycalculator.persistence.ExchangeRateLoader;
import moneycalculator.persistence.rest.ExchangeRateLoaderFromWebService;
import moneycalculator.view.Dialog;
import moneycalculator.view.swing.DialogSwing;

public class MCController implements ActionListener {
    private final Dialog dialogSwing;
    private final ExchangeRateLoader ERLFWS; //siglas de la clase

    public MCController(Dialog dialogSwing,
                        ExchangeRateLoader ERLFWS) {
        this.dialogSwing = dialogSwing;
        this.ERLFWS = ERLFWS;
        this.dialogSwing.registerController(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       Money money = (Money)this.dialogSwing.getMoney();
       Currency currencyFrom = money.getCurrency();
       Currency currencyTo = (Currency)this.dialogSwing.getCurrencyTo();
       ExchangeRate exchangeRate = this.ERLFWS.
               exchangerateLoader(currencyFrom, currencyTo);
       
       this.dialogSwing.refreshMoney(new Money(exchangeRate.getRate() *
               money.getAmount(), currencyTo));
    }
}
