package com.example.app_gymfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.app_gymfc.models.Evaluation;
import com.example.app_gymfc.ui.DatePickerFragment;
import com.example.app_gymfc.ui.EvaluationsAdapter;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class GetEvaluationsActivity extends AppCompatActivity {
    private Button btnGetDetail, btnGet, btnLogOut;
    private ListView lvAllEvaluations;
    private TextInputLayout tilDate1, tilDate2;
    private List<Evaluation> evaluationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_evaluations);
        btnGetDetail = findViewById(R.id.buttonActivityGetDetail);
        btnGet = findViewById(R.id.buttonMainActivityCreateEvaluation);
        btnLogOut = findViewById(R.id.buttonMainActivityLogOut);
        lvAllEvaluations = findViewById(R.id.activity_main_lv_all_evaluations);
        tilDate1 = findViewById(R.id.TextInputActivityGetEvaluationDateStart);
        tilDate2 = findViewById(R.id.TextInputActivityGetEvaluationDateEnd);
        tilDate1.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDate1);
        });
        tilDate2.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDate2);
        });

        for (int i = 0; i < 10; ++i) {
            Evaluation newEvaluation = new Evaluation(String.format("Date %d", i), String.format("Peso %d", i), String.format("Imc %d", i));
            newEvaluation.setId(i);
            evaluationList.add(newEvaluation);
        }
        EvaluationsAdapter adapter = new EvaluationsAdapter(this, evaluationList);
        lvAllEvaluations.setAdapter(adapter);
        
        btnGet.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), RegisterEvaluationActivity.class);
            startActivity(i);
            finish();
        });
        btnLogOut.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), LoginActivity.class);
            startActivity(i);
            finish();
        });

    }
}