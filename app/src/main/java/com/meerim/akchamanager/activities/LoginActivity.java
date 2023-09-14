package com.meerim.akchamanager.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.meerim.akchamanager.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtDontHave;
    private TextInputEditText edEmail, edPassword;
    private Button btnSignIn;
    private FirebaseAuth firebaseAuth;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        setupListeners();
        setupTextChangeListeners();

        btnSignIn.setOnClickListener(v -> checkEmailAndPassword());
        forgotPassword.setOnClickListener(v -> handleForgotPassword());
    }

    private void setupListeners() {
        txtDontHave.setOnClickListener(this);
    }

    private void setupTextChangeListeners() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        edEmail.addTextChangedListener(textWatcher);
        edPassword.addTextChangedListener(textWatcher);
    }

    private void handleForgotPassword() {
        // Implement your logic for forgot password here
    }

    private void checkEmailAndPassword() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
        if (edEmail.getText().toString().matches(emailPattern) && edPassword.length() >= 8) {
            disableButton();
            firebaseAuth.signInWithEmailAndPassword(edEmail.getText().toString(), edPassword.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            sendToMainActivity(user.getDisplayName());
                        } else {
                            enableButton();
                            Toast.makeText(LoginActivity.this, "Error signing in", Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Туура эмес электрондук почта же сыр сөз", Toast.LENGTH_SHORT).show();
        }
    }

    private void enableButton() {
        btnSignIn.setEnabled(true);
        btnSignIn.setTextColor(Color.rgb(255, 255, 255));
    }

    private void disableButton() {
        btnSignIn.setEnabled(false);
        btnSignIn.setTextColor(Color.argb(50, 255, 255, 255));
    }

    private void checkInputs() {
        boolean isEmailEmpty = TextUtils.isEmpty(edEmail.getText());
        boolean isPasswordEmpty = TextUtils.isEmpty(edPassword.getText());
        if (!isEmailEmpty && !isPasswordEmpty) {
            enableButton();
        } else {
            disableButton();
        }
    }

    private void initViews() {
        txtDontHave = findViewById(R.id.sign_in_dont_have);
        edEmail = findViewById(R.id.sign_in_email);
        edPassword = findViewById(R.id.sign_in_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        forgotPassword = findViewById(R.id.sign_in_forgot);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_dont_have) {
            startActivity(new Intent(this, SignupActivity.class));
        }
    }

    private void sendToMainActivity(String name) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    }
}
