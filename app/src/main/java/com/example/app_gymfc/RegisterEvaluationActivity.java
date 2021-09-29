package com.example.app_gymfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_gymfc.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterEvaluationActivity extends AppCompatActivity {
    private Button btnRegEv, btnBack;
    private TextInputLayout tilWeight, tilImc, tilDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_evaluation);
        btnRegEv = findViewById(R.id.buttonActivityRegisterUser);
        btnBack = findViewById(R.id.buttonActivityBack);
        tilDate = findViewById(R.id.TextInputActivityRegisterEvaluationDate);
        tilWeight = findViewById(R.id.TextInputActivityRegisterEvaluationWeight);
        tilImc = findViewById(R.id.TextInputActivityRegisterEvaluationImc);
        tilDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDate);
        });
        btnRegEv.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Registro Exítoso", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(view.getContext(), GetEvaluationsActivity.class);
            startActivity(i);
            finish();
        });
        btnBack.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), GetEvaluationsActivity.class);
            startActivity(i);
            finish();
        });
    }
}