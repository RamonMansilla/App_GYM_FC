package com.example.app_gymfc.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.app_gymfc.GetEvaluationsActivity;
import com.example.app_gymfc.LoginActivity;
import com.example.app_gymfc.MainActivity;
import com.example.app_gymfc.models.User;

public class AuthController {
    private Context ctx;

    public AuthController(Context ctx) {
        this.ctx = ctx;
    }

    public void registerUser(User user) {
        Toast.makeText(ctx, String.format("Usuario %s registrado!", user.getEmail()), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(i);
    }

    public void loginUser(String email, String password) {
        if (password.equals("123456")) {
            Toast.makeText(ctx, String.format("Bienvenido %s", email), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ctx, GetEvaluationsActivity.class);
            ctx.startActivity(i);
            ((Activity) ctx).finish();
        } else {
            Toast.makeText(ctx, String.format("La contraseña es incorrecta", email), Toast.LENGTH_SHORT).show();
        }
    }

}
//    public void loginUser(String email,String password) {
//
//        Intent i = new Intent(ctx, LoginActivity.class);
//        ctx.startActivity(i);
//    }