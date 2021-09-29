package com.example.app_gymfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

//como escribir los distintos elementos de la aplciación
//MiClase
//miVariable
//miFuncion
//MI_CONSTANTE
//mi_fichero.xml snake_case
//.my-div kebab-case
public class MainActivity extends AppCompatActivity {
    private Button btnRegister, btnGet, btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRegister = findViewById(R.id.buttonMainActivityRegisterUser);
        btnGet = findViewById(R.id.buttonMainActivityCreateEvaluation);
        btnLogOut = findViewById(R.id.buttonMainActivityLogOut);

        btnRegister.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), RegisterEvaluationActivity.class);
            startActivity(i);
            finish();
        });
        btnGet.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), GetEvaluationsActivity.class);
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