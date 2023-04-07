package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static Calculator.Polynomial.*;


public class Calculator extends JFrame {
    private JPanel panel1;
    private JButton computeButton;
    private JButton Button1;
    private JButton Button2;
    private JButton Button3;
    private JButton Button4;
    private JButton Button7;
    private JButton Button5;
    private JButton Button6;
    private JButton Button8;
    private JButton Button9;
    private JButton Button0;
    private JButton button11;
    private JButton button12;
    private JButton xButton;
    private JButton button15;
    private JButton delButton;
    public JTextField textField1;
    public JTextField textField2;
    private JLabel resultLabel;
    public JLabel resultField;
    private JToggleButton addButton;
    private JToggleButton subButton;
    private JToggleButton multiplyButton;
    private JToggleButton derivButton;
    private JToggleButton antiButton;
    private JToggleButton polyButton1;
    private JToggleButton polyButton2;
    public JFrame Calculator;
    Polynomial poly1 = new Polynomial();
    Polynomial poly2 = new Polynomial();
    Polynomial res = new Polynomial();
    public StringBuilder stringText1 = new StringBuilder();
    public StringBuilder stringText2 = new StringBuilder();


    public Calculator() {
        Calculator = new JFrame("Calculator");
        Calculator.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Calculator.setPreferredSize(new Dimension(1000, 800));
        Calculator.setResizable(false);


        Calculator.add(panel1);
        Calculator.pack();
        Calculator.setLocationRelativeTo(null);
        Calculator.setVisible(true);
        button15.setEnabled(false);
        delButton.setEnabled(false);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String buttonNumber = button.getText();
                if (polyButton1.isSelected()) {
                    if (textField1.getText().isEmpty()) {
                        button15.setEnabled(false);
                    }
                    stringText1.append(buttonNumber);
                    String resultString = stringText1.toString();
                    textField1.setText(resultString);

                } else if (polyButton2.isSelected()) {
                    if (textField2.getText().isEmpty()) {
                        button15.setEnabled(false);
                    }
                    stringText2.append(buttonNumber);
                    String resultString2 = stringText2.toString();
                    textField2.setText(resultString2);

                }
                button15.setEnabled(false);
                delButton.setEnabled(true);
                button12.setEnabled(true);
                button11.setEnabled(true);
                xButton.setEnabled(true);
            }
        };


        ActionListener actionListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton buttonTog = (JToggleButton) e.getSource();
                if (buttonTog == polyButton1) {
                    polyButton2.setSelected(false);
                } else {
                    polyButton1.setSelected(false);
                }
            }
        };
        polyButton1.addActionListener(actionListener1);
        polyButton2.addActionListener(actionListener1);


        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (polyButton2.isSelected()) {
                    stringText2.deleteCharAt(stringText2.length() - 1);
                    String result2 = new String();
                    result2 = stringText2.toString();
                    textField2.setText(result2);
                    if (textField2.getText().charAt(textField2.getText().length() - 1) != 'x') {
                        button15.setEnabled(false);
                    } else {
                        button15.setEnabled(true);
                    }
                    if (textField2.getText().isEmpty()) {
                        button15.setEnabled(false);
                        delButton.setEnabled(false);

                    } else {
                        delButton.setEnabled(true);

                    }
                } else if (polyButton1.isSelected()) {
                    stringText1.deleteCharAt(stringText1.length() - 1);
                    String result1 = new String();
                    result1 = stringText1.toString();
                    textField1.setText(result1);
                    if (textField1.getText().charAt(textField1.getText().length() - 1) != 'x') {
                        button15.setEnabled(false);
                    } else {
                        button15.setEnabled(true);
                    }

                    if (textField1.getText().isEmpty()) {
                        button15.setEnabled(false);
                        delButton.setEnabled(false);

                    } else {
                        delButton.setEnabled(true);

                    }
                }


            }
        });

        Button0.addActionListener(actionListener);
        Button1.addActionListener(actionListener);
        Button2.addActionListener(actionListener);
        Button3.addActionListener(actionListener);
        Button4.addActionListener(actionListener);
        Button5.addActionListener(actionListener);
        Button6.addActionListener(actionListener);
        Button7.addActionListener(actionListener);
        Button8.addActionListener(actionListener);
        Button9.addActionListener(actionListener);
        button11.addActionListener(actionListener);
        button12.addActionListener(actionListener);


        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button15.setEnabled(true);
                JButton button = (JButton) e.getSource();
                String buttonNumber = button.getText();
                if (polyButton1.isSelected()) {
                    if (textField1.getText().isEmpty()) {
                        button15.setEnabled(false);
                    }
                    stringText1.append(buttonNumber);
                    String resultString = stringText1.toString();
                    textField1.setText(resultString);

                } else if (polyButton2.isSelected()) {
                    if (textField2.getText().isEmpty()) {
                        button15.setEnabled(false);
                    }
                    stringText2.append(buttonNumber);
                    String resultString2 = stringText2.toString();
                    textField2.setText(resultString2);
                    delButton.setEnabled(true);
                    button12.setEnabled(true);
                    button11.setEnabled(true);
                }
            }
        });

        button15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xButton.setEnabled(false);
                button11.setEnabled(false);
                button12.setEnabled(false);
                button15.setEnabled(false);
                if (polyButton2.isSelected()) {
                    stringText2.append(button15.getText());
                    String res = stringText2.toString();
                    textField2.setText(res);
                } else if (polyButton1.isSelected()) {
                    stringText1.append(button15.getText());
                    String res = stringText1.toString();
                    textField1.setText(res);
                }
            }
        });

        computeButton.setPreferredSize(new Dimension(60, 50));
        addButton.setSelected(false);
        subButton.setSelected(false);
        computeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty()) {

                    String polynomialString1 = textField1.getText();
                    String polynomialString2 = textField2.getText();

                    poly1 = Polynomial.readInput(polynomialString1);
                    poly2 = Polynomial.readInput(polynomialString2);

                    if (addButton.isSelected()) {

                        res = Operations.addPolynomials(poly1, poly2);
                        String resStr = polyToString(res);
                        resultField.setText(resStr);
                        addButton.setSelected(false);
                    }

                    if (subButton.isSelected()) {

                        res = Operations.subtractPolynomials(poly1, poly2);
                        String resStr = polyToString(res);
                        resultField.setText(resStr);
                        subButton.setSelected(false);
                    }

                    if (multiplyButton.isSelected()) {
                        res = Operations.multiplyPolynomials(poly1, poly2);
                        String resStr = polyToString(res);
                        resultField.setText(resStr);
                        multiplyButton.setSelected(false);
                    }
                }


                if (derivButton.isSelected()) {
                    String polynomialString1 = textField1.getText();
                    String polynomialString2 = textField2.getText();
                    poly1 = Polynomial.readInput(polynomialString1);
                    poly2 = Polynomial.readInput(polynomialString2);

                    if (textField1.getText().length() == 0) {
                        res = Operations.derivativePolynomials(poly2);
                        String resStr = polyToString(res);
                        resultField.setText(resStr);
                        derivButton.setSelected(false);
                    } else {
                        res = Operations.derivativePolynomials(poly1);
                        String resStr = polyToString(res);
                        resultField.setText(resStr);
                        derivButton.setSelected(false);
                    }
                }

                if (antiButton.isSelected()) {
                    String polynomialString1 = textField1.getText();
                    String polynomialString2 = textField2.getText();
                    poly1 = Polynomial.readInput(polynomialString1);
                    poly2 = Polynomial.readInput(polynomialString2);
                    if (textField1.getText().length() == 0) {
                        res = Operations.antiderivativePolynomials(poly2);
                        String resStr = polyToString(res);
                        resultField.setText(resStr);
                        derivButton.setSelected(false);
                    } else {
                        res = Operations.antiderivativePolynomials(poly1);
                        String resStr = polyToString(res);
                        resultField.setText(resStr);
                        derivButton.setSelected(false);
                    }
                    antiButton.setSelected(false);
                }

            }
        });
    }
}

