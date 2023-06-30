package com.example.emiapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText principalAmountEditText, downPaymentEditText, interestRateEditText, loanTermEditText;
    Button calculateButton;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        principalAmountEditText = findViewById(R.id.principalAmountEditText);
        downPaymentEditText = findViewById(R.id.downPaymentEditText);
        interestRateEditText = findViewById(R.id.interestRateEditText);
        loanTermEditText = findViewById(R.id.loanTermEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double principalAmount = Double.parseDouble(principalAmountEditText.getText().toString());
                double downPayment = Double.parseDouble(downPaymentEditText.getText().toString());
                double interestRate = Double.parseDouble(interestRateEditText.getText().toString());
                double loanTerm = Double.parseDouble(loanTermEditText.getText().toString());

                double loanAmount = principalAmount - downPayment;
                double monthlyInterestRate = (interestRate/100)/12;
                double totalPayments = loanTerm;
                double emi = calculateEMI(loanAmount, monthlyInterestRate, totalPayments);

                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String result = "Monthly EMI: $" + decimalFormat.format(emi);
                resultTextView.setText(result);
            }
        });
    }

    private double calculateEMI(double loanAmount, double monthlyInterestRate, double totalPayments) {
        double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalPayments)) /
                (Math.pow(1 + monthlyInterestRate, totalPayments) - 1);
        return emi;
    }
}
