package com.sekhar.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sekhar.android.calculator.view.ViewEditText;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Created by sekhar on 08-07-2016.
 */
public class BasicCalculator extends Activity {

    Button btnBackSpace;
    Button btnC;

    Button btnAdd;
    Button btnSub;
    Button btnMult;
    Button btnDiv;
    TextView txtResultScreen;
    ViewEditText txtDisplayScreen;
    StringBuilder temporaryCalculation = new StringBuilder();
    private int[] numericButtons = {R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive,
            R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine, R.id.btnDot, R.id.btnDblZero};
    private int[] operatorButtons = {R.id.btnAdd, R.id.btnSub, R.id.btnMult, R.id.btnDiv};
    private int backspaceButton = R.id.btnBackSpace;
    private int cButton = R.id.btnC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnBackSpace = (Button) findViewById(R.id.btnBackSpace);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnDiv = (Button) findViewById(R.id.btnDiv);

        txtDisplayScreen = (ViewEditText) findViewById(R.id.txtCalcDisplay);
        txtResultScreen = (TextView) findViewById(R.id.txtResult);
        txtResultScreen.setText("0");

        setOperandOnClickListener();
        setOperatorOnClickListener();
        setBackspaceOnClickListener();
        setBtnCOnClickListener();
    }

    private void setBtnCOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDisplayScreen.setText("");
                temporaryCalculation.setLength(0);
                txtResultScreen.setText("0");
            }
        };

        findViewById(cButton).setOnClickListener(listener);
    }

    private void setBackspaceOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dispText = txtDisplayScreen.getText().toString();

                if (dispText != null && dispText.length() > 0) {
                    txtDisplayScreen.deletePrevCharCursorLocation();
                    txtResultScreen.setText(calculate(txtDisplayScreen.getText().toString()));
                }
            }
        };

        findViewById(backspaceButton).setOnClickListener(listener);
    }

    private void setOperandOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                txtDisplayScreen.appendToCursorLocation(button.getText().toString());
                txtResultScreen.setText(calculate(txtDisplayScreen.getText().toString()));
            }
        };

        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;

                if (!isPrevCharacterOperator()) { // Can't be 2 operators together
                    txtDisplayScreen.appendToCursorLocation(button.getText().toString());
                }
            }
        };

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    /**
     * Check if the previous character is already an operator
     * This is to prevent inserting 2 operators consecutively
     *
     * @return
     */
    private boolean isPrevCharacterOperator() {
        int start = Math.max(txtDisplayScreen.getSelectionStart(), 0);

        Editable dispText = txtDisplayScreen.getText();
        if (!Character.isDigit(dispText.charAt(start - 1))) { // Can't be 2 operators together
            return true;
        } else {
            return false;
        }
    }

    private String calculate(String expr) {
        double result = 0.0;
        String resultString = "0";
        if (expr != null && expr.length() > 0) {
            // First & Last character can't be non-numeric
            if (Character.isDigit(expr.charAt(0)) && Character.isDigit(expr.charAt(expr.length() - 1))) {
                result = evaluateExpr(expr);
            }
            return Double.toString(result);
        } else {
            return resultString;
        }
    }

    private double evaluateExpr(String expr) {
        Expression expression = new ExpressionBuilder(expr).build();
        double result = 0;
        try {
            result = (double) expression.evaluate();
        } catch (ArithmeticException ex) {
            txtResultScreen.setText("Error");
        }
        return result;
    }
}