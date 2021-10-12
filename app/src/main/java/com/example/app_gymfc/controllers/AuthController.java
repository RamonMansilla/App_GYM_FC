package com.example.app_gymfc.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.app_gymfc.MainActivity;
import com.example.app_gymfc.LoginActivity;
import com.example.app_gymfc.dao.UserDao;
import com.example.app_gymfc.lib.BCrypt;
import com.example.app_gymfc.lib.GymFcDataBase;
import com.example.app_gymfc.models.User;
import com.example.app_gymfc.models.UserEntity;
import com.example.app_gymfc.models.UserMapper;

import java.util.Date;

public class AuthController {

    private final String KEY_USER_ID = "userId";
    private final String KEY_EMAIL = "userEmail";
    private final String KEY_FIRST_NAME = "userFirstName";
    private final String KEY_LAST_NAME = "userLastName";
    private final String KEY_HEIGHT = "userHeight";

    private UserDao userDao;

    private Context ctx;
    private SharedPreferences preferences;

    public AuthController(Context ctx) {
        this.ctx = ctx;
        int PRIVATE_MODE = 0;
        String PREF_NAME = "AppGymPref";
        this.preferences = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.userDao = GymFcDataBase.getInstance(ctx).userDao();


    }

    private void setUserSession(User user) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putLong(KEY_USER_ID, user.getId());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_FIRST_NAME, user.getFirstName());
        editor.putString(KEY_LAST_NAME, user.getLastName());
        editor.putString(KEY_HEIGHT, user.getHeight());
        editor.apply();
    }

    public void checkUserSession() {
        long id = preferences.getLong(KEY_USER_ID, 0);
        if (id != 0) {
            Intent i = new Intent(ctx, MainActivity.class);
            ctx.startActivity(i);
            ((Activity) ctx).finish();
        }
    }

    public User getUserSession() {
        long id = preferences.getLong(KEY_USER_ID, 0);
        String firstName = preferences.getString(KEY_FIRST_NAME, "");
        String lastName = preferences.getString(KEY_LAST_NAME, "");
        String email = preferences.getString(KEY_EMAIL, "");
        String height = preferences.getString(KEY_HEIGHT, "");
        User user = new User(firstName, lastName, email, new Date(), height);
        user.setId(id);
        return user;
    }

    public void registerUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        UserEntity userEntity = new UserMapper(user).toEntity();
        userDao.insert(userEntity);

        Toast.makeText(ctx, String.format("Usuario %s registrado!", user.getEmail()), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(i);
    }

    public void loginUser(String email, String password) {
        UserEntity userEntity = userDao.findByEmail(email);
        User user = new UserMapper(userEntity).toBase();

        if (BCrypt.checkpw(password, user.getPassword())) {
            setUserSession(user);
            Toast.makeText(ctx, String.format("Bienvenido %s", user.getFirstName()), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ctx, MainActivity.class);
            ctx.startActivity(i);
            ((Activity) ctx).finish();
        } else {
            Toast.makeText(ctx, String.format("La contraseña es incorrecta", email), Toast.LENGTH_SHORT).show();
        }
    }

    public void logout() {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.clear();
        editor.apply();
        Toast.makeText(ctx, "Cerrando Sesión", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ctx, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
        ((Activity) ctx).finish();
    }

}