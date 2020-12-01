package com.example.diplomaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {

    private Button btnEarPlugs, btnSmartAdvice, btnBackground, btnCalibrate;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        findUIElements();
        checkUserAndSetEmail();

        btnEarPlugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int status = (Integer) v.getTag();
                switch (status) {
                    case 0:
                        btnEarPlugs.setText("yes");
                        v.setTag(1);
                        break;
                    case 1:
                        btnEarPlugs.setText("no");
                        v.setTag(0);
                        break;
                    default:
                        break;
                }
            }
        });

        btnSmartAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int status = (Integer) v.getTag();
                switch (status) {
                    case 0:
                        btnSmartAdvice.setText("on");
                        v.setTag(1);
                        break;
                    case 1:
                        btnSmartAdvice.setText("off");
                        v.setTag(0);
                        break;
                    default:
                        break;
                }
            }
        });

        btnBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int status = (Integer) v.getTag();
                switch (status) {
                    case 0:
                        btnBackground.setText("on");
                        v.setTag(1);
                        break;
                    case 1:
                        btnBackground.setText("off");
                        v.setTag(0);
                        break;
                    default:
                        break;
                }
            }
        });


    }

    public void openDecibels(View view) {
        Intent intent = new Intent(UserActivity.this, DecibelsActivity.class);
        startActivity(intent);
    }

    private void findUIElements(){
        btnEarPlugs = findViewById(R.id.earplugsButton);
        btnEarPlugs.setTag(1);
        btnBackground = findViewById(R.id.backgroundButton);
        btnBackground.setTag(1);
        btnCalibrate = findViewById(R.id.calibrateButton);
        btnSmartAdvice = findViewById(R.id.adviceButton);
        btnSmartAdvice.setTag(1);
        tvEmail = findViewById(R.id.emailTextView);
    }

    private void checkUserAndSetEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            tvEmail.setText(email);
        }
    }

}