package com.example.app_gymfc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app_gymfc.controllers.AuthController;
import com.example.app_gymfc.controllers.EvaluationController;
import com.example.app_gymfc.models.Evaluation;
import com.example.app_gymfc.models.User;
import com.example.app_gymfc.ui.DatePickerFragment;
import com.example.app_gymfc.ui.EvaluationsAdapter;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnCreate, btnLogOut, btnFilter;
    private ListView lvAllEvaluations;
    private TextView tvTitle, tvClearFilter;
    private TextInputLayout tilDate1, tilDate2;
    private List<Evaluation> evaluationList = new ArrayList<>();
    private AuthController authController;
    private EvaluationController evaluationController;
    private final String DATE_PATTERN = "yyyy-MM-dd";
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authController = new AuthController(this);
        evaluationController = new EvaluationController(this);

        tvTitle = findViewById(R.id.activity_main_tv_title);
        lvAllEvaluations = findViewById(R.id.activity_main_lv_all_evaluations);
        tvClearFilter = findViewById(R.id.activity_main_clear_filters);

        btnCreate = findViewById(R.id.buttonMainActivityCreateEvaluation);
        btnLogOut = findViewById(R.id.buttonMainActivityLogOut);
        btnFilter = findViewById(R.id.buttonActivityGetDetail);
        User user = authController.getUserSession();

        tilDate1 = findViewById(R.id.TextInputActivityGetEvaluationDateStart);
        tilDate2 = findViewById(R.id.TextInputActivityGetEvaluationDateEnd);
        tilDate1.getEditText().setOnClickListener(view -> DatePickerFragment.showDatePickerDialog(this, tilDate1));
        tilDate2.getEditText().setOnClickListener(view -> DatePickerFragment.showDatePickerDialog(this, tilDate2));


        tvTitle.setText(String.format("Evaluaciones de %s", user.getFirstName()));

        List<Evaluation> evaluationList = evaluationController.getAll();
        EvaluationsAdapter adapter = new EvaluationsAdapter(this, evaluationList);


        lvAllEvaluations.setAdapter(adapter);

        lvAllEvaluations.setOnItemClickListener((adapterView, view, index, list) -> {
            Evaluation evaluation = evaluationList.get(index);
            Intent i = new Intent(view.getContext(), DetailActivity.class);
            i.putExtra("evaluation", evaluation);
            view.getContext().startActivity(i);
        });


        btnCreate.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), RegisterEvaluationActivity.class);
            startActivity(i);
            finish();
        });
        btnFilter.setOnClickListener(view -> {
            String fromStr = tilDate1.getEditText().getText().toString();
            String toStr = tilDate2.getEditText().getText().toString();

            boolean validFrom = !fromStr.isEmpty();
            boolean validTo = !toStr.isEmpty();

            if (validFrom && validTo) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
                try {
                    Date from = dateFormatter.parse(fromStr);
                    Date to = dateFormatter.parse(toStr);
                    List<Evaluation> evaluationRangeList = evaluationController.getRange(from, to);
                    EvaluationsAdapter rangeAdapter = new EvaluationsAdapter(this, evaluationRangeList);
                    lvAllEvaluations.setAdapter(rangeAdapter);
                    lvAllEvaluations.setOnItemClickListener((adapterView, rangeView, index, l) -> {
                        Evaluation evaluation = evaluationRangeList.get(index);
                        Intent i = new Intent(rangeView.getContext(), DetailActivity.class);
                        i.putExtra("evaluation", evaluation);
                        rangeView.getContext().startActivity(i);
                    });

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        btnLogOut.setOnClickListener(view -> {

            authController.logout();
        });
        tvClearFilter.setOnClickListener(view -> {
            tilDate1.getEditText().setText("");
            tilDate2.getEditText().setText("");
            lvAllEvaluations.setAdapter(adapter);
        });
    }
}