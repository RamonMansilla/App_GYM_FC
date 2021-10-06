package com.example.app_gymfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_gymfc.controllers.AuthController;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    private TextInputLayout tilEmail, tilPassword;
    private AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authController = new AuthController(this);
        authController.checkUserSession();

        btnLogin = findViewById(R.id.buttonActivityLogin);
        btnRegister = findViewById(R.id.buttonActivityRegister);
        tilEmail = findViewById(R.id.TextInputUserActivityLogin);
        tilPassword = findViewById(R.id.TextInputPassActivityLogin);

        btnLogin.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Iniciando sesión", Toast.LENGTH_SHORT).show();
            String email = tilEmail.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();
            boolean emailValid = !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
            boolean passwordValid = !password.isEmpty();

            if (!emailValid) {
                tilEmail.setError("El email es inválido");
            } else {
                tilEmail.setError(null);
                tilEmail.setErrorEnabled(false);
            }
            if (!passwordValid) {
                tilPassword.setError("Campo requerido");
            } else {
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(false);
            }
            if (emailValid && passwordValid) {
                AuthController controller = new AuthController(view.getContext());
                controller.loginUser(email, password);
            } else {
                Toast.makeText(view.getContext(), "Campos Inválidos", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), RegisterUserActivity.class);
            startActivity(i);
            finish();
        });

    }
}