package com.example.app_gymfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_gymfc.models.Evaluation;
import com.example.app_gymfc.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterEvaluationActivity extends AppCompatActivity {
    private Button btnRegEv, btnBack;
    private TextInputLayout tilWeight, tilImc, tilDate;
    private List<Evaluation> evaluationList = new ArrayList<>();

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

        for (int x = 0; x < 10; x++) {
            Evaluation newEvaluation = new Evaluation(x, new Date(), 75);
            newEvaluation.setId(x);
            evaluationList.add(newEvaluation);

        }

        btnRegEv.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Registro Exítoso", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
            finish();
        });
        btnBack.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
            finish();
        });
    }
}