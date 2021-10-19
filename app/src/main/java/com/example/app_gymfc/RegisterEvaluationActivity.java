package com.example.app_gymfc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_gymfc.controllers.AuthController;
import com.example.app_gymfc.controllers.EvaluationController;
import com.example.app_gymfc.models.Evaluation;
import com.example.app_gymfc.models.User;
import com.example.app_gymfc.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterEvaluationActivity extends AppCompatActivity {
    private Button btnRegEv, btnBack;
    private final String DATE_PATTERN = "yyyy-MM-dd";
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
        tilDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDate);
        });

        btnRegEv.setOnClickListener(view -> {
            String date = tilDate.getEditText().getText().toString();
            String weitghS = tilWeight.getEditText().getText().toString();
            //TODO: VALIDACIONES!
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
            Date dateEv = null;
            double weitgh = Double.parseDouble(weitghS);
            try {
                dateEv = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            AuthController authController = new AuthController(view.getContext());
            User user = authController.getUserSession();
            Evaluation evaluation = new Evaluation(dateEv, weitgh,user.getId());
            EvaluationController controller = new EvaluationController(view.getContext());
            controller.register(evaluation);
            Toast.makeText(view.getContext(), "Registro Exítoso", Toast.LENGTH_SHORT).show();
        });
        btnBack.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
            finish();
        });
    }
}