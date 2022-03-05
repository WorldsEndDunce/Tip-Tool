package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText billAmount;
    private android.widget.SeekBar seekBar;
    private TextView seekbarText;
    private Button button;
    private TextView tipText;
    private TextView totalText;
    private Button smalltip;
    private Button medtip;
    private Button biggertip;
    private int seekbarPercentage;
    private Float bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = findViewById(R.id.billAmount);
        seekBar = findViewById(R.id.seekBar);
        seekbarText = findViewById(R.id.seekBarText);
        button = findViewById(R.id.button);
        tipText = findViewById(R.id.tipText);
        totalText = findViewById(R.id.totalText);
        smalltip = findViewById(R.id.smalltip);
        medtip = findViewById(R.id.medtip);
        biggertip = findViewById(R.id.biggertip);

        seekbarText.setText(String.valueOf(seekBar.getProgress())+"%");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbarText.setText(String.valueOf(seekBar.getProgress())+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekbarPercentage = seekBar.getProgress();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
        smalltip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBar.setProgress(10);
                seekbarPercentage = 10;
                calculate();
            }
        });

        medtip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBar.setProgress(15);
                seekbarPercentage = 15;
                calculate();
            }
        });

        biggertip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBar.setProgress(20);
                seekbarPercentage = 20;
                calculate();
            }
        });
    }

    public void calculate() {
        float result;
        if (!isValid(billAmount.getText().toString())) Toast.makeText(this, "That isn't a valid bill amount", Toast.LENGTH_SHORT).show();
        else if (!billAmount.getText().toString().equals("")) {
            bill = Float.parseFloat(billAmount.getText().toString());
            result = bill * seekbarPercentage/100;

            if (String.valueOf(result + bill).equals("Infinity")) {
                Toast.makeText(this, "That's too big of a number >.>", Toast.LENGTH_SHORT).show();
                return;
            }
            tipText.setText("Tip: $"+roundOffTo2DecPlaces(result));
            totalText.setText("Total: $"+roundOffTo2DecPlaces(result + bill));
        }
        else {
            Toast.makeText(this, "Please enter your bill amount", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValid(String str) {
        int countDecimals = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.' && countDecimals == 0) {
                countDecimals = i;
                continue;
            }
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
    String roundOffTo2DecPlaces(float val)
    {
        return String.format("%.2f", val);
    }
}