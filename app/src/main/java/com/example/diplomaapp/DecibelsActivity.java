package com.example.diplomaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DecibelsActivity extends AppCompatActivity {

    private TextView tvDecibels;
    private Button btnPlus,btnMinus;
    private int decibels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decibels);
        findUIElements();
        decibels = Integer.parseInt(tvDecibels.getText().toString());
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decibels += 1;
                tvDecibels.setText(String.valueOf(decibels));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decibels -= 1;
                tvDecibels.setText(String.valueOf(decibels));
            }
        });
    }

    private void findUIElements(){
       tvDecibels = findViewById(R.id.decibelsNumberTextView);
       btnPlus = findViewById(R.id.plusButton);
       btnMinus = findViewById(R.id.minusButton);
    }
}