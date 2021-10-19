package com.example.app_gymfc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_gymfc.controllers.AuthController;
import com.example.app_gymfc.controllers.EvaluationController;
import com.example.app_gymfc.models.Evaluation;
import com.example.app_gymfc.models.User;

public class DetailActivity extends AppCompatActivity {
    private TextView tvId, tvEvDate, tvEvWeight, tvEvImc;
    private Button btnDel, btnBack;
    private AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        authController = new AuthController(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Evaluation evaluation = (Evaluation) getIntent().getSerializableExtra("evaluation");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        tvId = findViewById(R.id.activity_detail_tv_id);
        User user = authController.getUserSession();
        tvId.setText(String.format("Evaluacion de %s", user.getFirstName()));;
        tvEvDate = findViewById(R.id.TextInputActivityRegisterEvaluationDate);
        tvEvWeight = findViewById(R.id.TextViewWeight);
        tvEvImc = findViewById(R.id.TextViewImc);
        btnDel = findViewById(R.id.buttonActivityDeleteUser);
        btnBack = findViewById(R.id.buttonActivityBack);
        tvEvDate.setText(dateFormat.format(evaluation.getDate()));
        tvEvWeight.setText(Double.toString(evaluation.getWeight()));
        tvEvImc.setText(Double.toString(evaluation.getWeight() / (1.70 * 1.70)));

        btnDel.setOnClickListener(view -> {
            EvaluationController controller = new EvaluationController(view.getContext());
            controller.delete(evaluation.getId());
        });
        btnBack.setOnClickListener(view -> {
            super.onBackPressed();
        });

    }

}
