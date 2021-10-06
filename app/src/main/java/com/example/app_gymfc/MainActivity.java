package com.example.app_gymfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app_gymfc.controllers.AuthController;
import com.example.app_gymfc.models.Evaluation;
import com.example.app_gymfc.models.User;
import com.example.app_gymfc.ui.DatePickerFragment;
import com.example.app_gymfc.ui.EvaluationsAdapter;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnGet, btnLogOut;
    private ListView lvAllEvaluations;
    private TextView tvTitle;
    private TextInputLayout tilDate1, tilDate2;
    private List<Evaluation> evaluationList = new ArrayList<>();
    private AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authController = new AuthController(this);

        tvTitle = findViewById(R.id.activity_main_tv_title);
        lvAllEvaluations = findViewById(R.id.activity_main_lv_all_evaluations);

        btnGet = findViewById(R.id.buttonMainActivityCreateEvaluation);
        btnLogOut = findViewById(R.id.buttonMainActivityLogOut);
        User user = authController.getUserSession();

        tilDate1 = findViewById(R.id.TextInputActivityGetEvaluationDateStart);
        tilDate2 = findViewById(R.id.TextInputActivityGetEvaluationDateEnd);
        tilDate1.getEditText().setOnClickListener(view -> DatePickerFragment.showDatePickerDialog(this, tilDate1));
        tilDate2.getEditText().setOnClickListener(view -> DatePickerFragment.showDatePickerDialog(this, tilDate2));


        tvTitle.setText(String.format("Evaluaciones de %s", user.getFirstName()));


        for (int x = 0; x < 10; x++) {
            Evaluation newEvaluation = new Evaluation(x, new Date(), 75);
            newEvaluation.setId(x);
            evaluationList.add(newEvaluation);
        }

        EvaluationsAdapter adapter = new EvaluationsAdapter(this, evaluationList);


        lvAllEvaluations.setAdapter(adapter);

        lvAllEvaluations.setOnItemClickListener((adapterView, view, index, list) -> {
            Evaluation evaluation = evaluationList.get(index);
            Intent i = new Intent(view.getContext(), DetailActivity.class);
            i.putExtra("evaluation",evaluation);
            view.getContext().startActivity(i);
        });
//        lvAllEvaluations.setOnClickListener(((adapterView, view, i, l) -> {
//
//        }));

        btnGet.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), RegisterEvaluationActivity.class);
            startActivity(i);
            finish();
        });
        btnLogOut.setOnClickListener(view -> {
            authController.logout();
        });
    }
}