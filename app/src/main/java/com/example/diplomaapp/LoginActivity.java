package com.example.diplomaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etEmail, etPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        mLoadingBar = new ProgressDialog(LoginActivity.this);

        etEmail = findViewById(R.id.emailEditText);
        etPassword = findViewById(R.id.passwordEditText);
        btnLogin = findViewById(R.id.loginButton);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputs();
            }
        });
    }

    public void openSignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void checkInputs() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty() || !email.contains("@")) {
            showError(etEmail, "Email is not valid!");
        } else if (password.isEmpty() || password.length() <= 6) {
            showError(etPassword, "Password is not valid! (Password should be at least 6 characters");
        } else {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Wait while we log you in!");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                                mLoadingBar.dismiss();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
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