package com.example.app_gymfc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_gymfc.models.Evaluation;

public class DetailActivity extends AppCompatActivity {
    private TextView tvId;
    private Button btnDel, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Evaluation evaluation = (Evaluation) getIntent().getSerializableExtra("evaluation");
        tvId = findViewById(R.id.activity_detail_tv_id);
        btnDel = findViewById(R.id.buttonActivityDeleteUser);
        btnBack = findViewById(R.id.buttonActivityBack);

        tvId.setText(Long.toString(evaluation.getId()));

        btnBack.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
            finish();
        });

    }

}
