package com.example.diplomaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private Button btnSignUp;
    private EditText etEmail, etPassword, etConfirmPassword;

    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(SignUpActivity.this);

        etEmail = findViewById(R.id.emailEditText);
        etPassword = findViewById(R.id.passwordEditText);
        etConfirmPassword = findViewById(R.id.confirmPasswordEditText);
        btnSignUp = findViewById(R.id.signUpButton);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputs();
            }
        });
    }

    public void openMain(View view) {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void openLogin(View view) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void checkInputs(){
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        String confirmPassword=etConfirmPassword.getText().toString();

        if (email.isEmpty() || !email.contains("@")) {
            showError(etEmail, "Email is not valid!");
        }
        else if (password.isEmpty() || password.length() <= 6) {
            showError(etPassword, "Password is not valid! (Password should be at least 6 characters");
        }
        else if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            showError(etConfirmPassword, "Password does not match!");
        }
        else {
            mLoadingBar.setTitle("Sign up");
            mLoadingBar.setMessage("Wait while we sign you up!");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                                mLoadingBar.dismiss();
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUpActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                mLoadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    private void showError(EditText input, String s){
        input.setError(s);
        input.requestFocus();
    }


}