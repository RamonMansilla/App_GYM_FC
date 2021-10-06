package com.example.app_gymfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_gymfc.controllers.AuthController;
import com.example.app_gymfc.models.User;
import com.example.app_gymfc.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RegisterUserActivity extends AppCompatActivity {
    private final String DATE_PATTERN = "yyyy-MM-dd";
    private Button btnReg;
    private TextInputLayout tilFirstName, tilLastName, tilHeight, tilEmail, tilPassword, tilBirthDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        tilFirstName = findViewById(R.id.TextInputActivityRegisterName);
        tilLastName = findViewById(R.id.TextInputActivityRegisterUserLastName);
        tilBirthDay = findViewById(R.id.TextInputActivityRegisterDateBirth);
        tilHeight = findViewById(R.id.TextInputActivityRegisterHeight);
        tilEmail = findViewById(R.id.TextInputActivityRegisterEmail);
        tilPassword = findViewById(R.id.TextInputActivityRegisterPass);
        btnReg = findViewById(R.id.buttonActivityRegisterUser);

        tilBirthDay.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilBirthDay);
        });

        btnReg.setOnClickListener(view -> {

            String firstName = tilFirstName.getEditText().getText().toString();
            String lastName = tilLastName.getEditText().getText().toString();
            String birthday = tilBirthDay.getEditText().getText().toString();
            String height = tilHeight.getEditText().getText().toString();
            String emailUser = tilEmail.getEditText().getText().toString();
            String passUser = tilPassword.getEditText().getText().toString();
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
            Date birthdayDate = null;
            try {
                birthdayDate = dateFormatter.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            User user = new User(firstName, lastName, emailUser, birthdayDate, height);
            user.setPassword(passUser);

            AuthController controller = new AuthController(view.getContext());
            controller.registerUser(user);

            Toast.makeText(view.getContext(), "Usuario: "+emailUser+" registrado", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(view.getContext(), LoginActivity.class);
            startActivity(i);
            finish();
        });
    }
}