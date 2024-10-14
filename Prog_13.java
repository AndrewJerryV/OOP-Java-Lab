import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JLabel displayField;
    private StringBuilder input;
    private double num1, num2;
    private String operator;

    public Calculator() {

        setTitle("Calculator");
        setSize(300, 400);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        input = new StringBuilder();
        operator = "";

        displayField = new JLabel("0");
        displayField.setHorizontalAlignment(JLabel.RIGHT);
        displayField.setFont(new Font("Arial", Font.PLAIN, 20));
        displayField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
                input.append(command);
                displayField.setText(input.toString());
            } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                num1 = Double.parseDouble(input.toString());
                operator = command;
                input.setLength(0);
            } else if (command.equals("=")) {
                num2 = Double.parseDouble(input.toString());
                double result = performCalculation(num1, num2, operator);
                displayField.setText(String.valueOf(result));
                input.setLength(0);
            } else if (command.equals("C")) {
                input.setLength(0);
                displayField.setText("");
                num1 = 0;
                num2 = 0;
                operator = "";
            }
        } catch (NumberFormatException ex) {
            displayField.setText("Error");
        } catch (ArithmeticException ex) {
            displayField.setText("Cannot divide by zero");
        }
    }

    private double performCalculation(double num1, double num2, String operator) throws ArithmeticException {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return num1 / num2;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
