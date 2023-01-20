package moneycalculator;

import java.util.List;
import javax.swing.SwingUtilities;
import moneycalculator.control.MCController;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.persistence.CurrencyLoader;
import moneycalculator.persistence.files.CurrencyLoaderFromFile;
import moneycalculator.persistence.rest.ExchangeRateLoaderFromWebService;
import moneycalculator.view.swing.DialogSwing;
import moneycalculator.view.swing.MoneyCalculatorGUISwing;

public class Main {

    public static void main(String[] args) {
        System.out.println("MoneyCalculator...");
        CurrencyLoader currencyLoaderFromFile = 
                new CurrencyLoaderFromFile("currencies.txt");
        List<Currency> currencies = currencyLoaderFromFile.currencyLoader();
        
        ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService = 
                new ExchangeRateLoaderFromWebService();

        DialogSwing dialogSwing = new DialogSwing(currencies);
        
        new MCController(dialogSwing, 
                         exchangeRateLoaderFromWebService);
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MoneyCalculatorGUISwing(dialogSwing, 
                        "Money Calculator Display...");        
            }
        });
    }
}

