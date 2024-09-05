package com.example.samudra20.LoginSignup.finalloginpage;



import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.samudra20.Home.Main.OpeningPageMap;
import com.example.samudra20.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioLogin, radioRegister;
    private TextInputLayout inputEmail, inputPassword;
    private TextInputEditText etEmail, etPassword;
    private CheckBox checkRememberMe;
    private Button btnLogin;
    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        radioGroup = findViewById(R.id.radioGroup);
        radioLogin = findViewById(R.id.radioLogin);
        radioRegister = findViewById(R.id.radioRegister);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        checkRememberMe = findViewById(R.id.checkRememberMe);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        // Set listeners
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Forgot Password clicked", Toast.LENGTH_SHORT).show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioRegister) {
                    // Navigate to Register screen
                    Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Email is required");
            return;
        } else {
            inputEmail.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Password is required");
            return;
        } else {
            inputPassword.setError(null);
        }

        // Perform login logic (e.g., authenticate with a server)
        Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();

        // For demo purposes, just show a success message
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

        // You can start a new activity or update the UI as needed

        try {
            Intent intent = new Intent(LoginActivity.this, OpeningPageMap.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error starting the map activity: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
