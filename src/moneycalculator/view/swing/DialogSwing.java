package moneycalculator.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import moneycalculator.control.MCController;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.view.Dialog;

public class DialogSwing extends JPanel implements Dialog {
    private final String CALCULATE_BUTTON_LABEL = "calculate";
    
    private final String MONEY_LABEL = "Money";
    private final String CURRENCY_FROM_LABEL = "Currency from";
    private final String CURRENCY_TO_LABEL = "Currency to";
    private final String MoneyResult_Label = "Money Result = ";
    
    private final int MONEY_WIDTH = 40;
    private final int CURRENCY_FROM_WIDTH = 40;
    private final int CURRENCY_TO_WIDTH = 45;

    private JLabel moneyLabel, currencyFromLabel, currencyToLabel, 
            display, resultLabel;
    private JTextField moneyField;
    private JComboBox<Currency> currencyFromComboBox;
    private JComboBox<Currency> currencyToComboBox;
    private JButton calculateButton;

    private List<Currency> currencies;
    
    public DialogSwing(List<Currency> currencies) {
        this.currencies = currencies;
        createComponentsGUI();
    }

    @Override
    public Money getMoney() {
        try{
        return new Money(Double.parseDouble(this.moneyField.getText()), 
                         this.getCurrencyFrom());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, 
                    "Please enter a number into Money",
               "Error Message", JOptionPane.WARNING_MESSAGE);
            return new Money(0.0,this.getCurrencyFrom());
        }
    }

    @Override
    public Currency getCurrencyTo() {
        return (Currency) this.currencyToComboBox.getSelectedItem();
    }

    public void registerController(MCController mCController) {
        this.calculateButton.addActionListener((ActionListener) mCController);        
    }
    
    private void createComponentsGUI() {
        this.calculateButton = new JButton(this.CALCULATE_BUTTON_LABEL);
        
        this.moneyLabel = new JLabel(this.MONEY_LABEL);
        this.moneyField = new JTextField(this.MONEY_WIDTH);
        
        this.currencyFromLabel = new JLabel(this.CURRENCY_FROM_LABEL);
        this.currencyFromComboBox = new JComboBox<Currency>();
        this.currencyToLabel = new JLabel(this.CURRENCY_TO_LABEL);
        this.currencyToComboBox = new JComboBox<Currency>();
        this.moneyLabel = new JLabel(this.MONEY_LABEL);
        this.resultLabel = new JLabel(this.MoneyResult_Label);
        resultLabel.setFont(new Font("Italic", Font.PLAIN, 16));
        resultLabel.setForeground(Color.PINK);
        this.display = new JLabel();
        for (Currency currency : this.currencies) {
            this.currencyFromComboBox.addItem(currency);
            this.currencyToComboBox.addItem(currency);
        }
        
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.X_AXIS));

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(0, 1));

        labelPanel.add(this.moneyLabel);
        labelPanel.add(this.currencyFromLabel);
        labelPanel.add(this.currencyToLabel);
        labelPanel.add(this.resultLabel);
        
        dialogPanel.add(labelPanel);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(0, 1));

        fieldPanel.add(this.moneyField);
        fieldPanel.add(this.currencyFromComboBox);
        fieldPanel.add(this.currencyToComboBox);
        fieldPanel.add(this.display);
        dialogPanel.add(fieldPanel);

        JPanel controlPanel = new JPanel();
        
        controlPanel.add(this.calculateButton);
        
        setLayout(new BorderLayout());
        add(dialogPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.CENTER);
    }
    
    private Currency getCurrencyFrom() {
        return (Currency) this.currencyFromComboBox.getSelectedItem();
    }
    
    public void refreshMoney(Money money) {
        this.display.setText(money.getAmount() + 
                             " " + 
                             money.getCurrency().getSymbol());
    }
}
