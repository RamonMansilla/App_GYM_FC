package com.example.app_gymfc.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.example.app_gymfc.MainActivity;
import com.example.app_gymfc.dao.EvaluationDao;
import com.example.app_gymfc.lib.GymFcDataBase;
import com.example.app_gymfc.models.Evaluation;
import com.example.app_gymfc.models.EvaluationEntity;
import com.example.app_gymfc.models.EvaluationMapper;
import com.example.app_gymfc.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EvaluationController {
    private Context ctx;
    private EvaluationDao evaluationDao;


    public EvaluationController(Context ctx) {
        this.ctx = ctx;
        this.evaluationDao = GymFcDataBase.getInstance(ctx).evaluationDao();
    }

    public void register(Evaluation evaluation) {
        EvaluationMapper mapper = new EvaluationMapper(evaluation);
        EvaluationEntity newEv = mapper.toEntity();
        evaluationDao.insert(newEv);
        Intent i = new Intent(ctx, MainActivity.class);
        ctx.startActivity(i);
        ((Activity) ctx).finish();
        Toast.makeText(ctx, "Registrar", Toast.LENGTH_SHORT).show();
    }

    public void delete(long id) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        evaluationDao.delete(id);
                        Intent i = new Intent(ctx, MainActivity.class);
                        ctx.startActivity(i);
                        ((Activity) ctx).finish();
                    } catch (Error error) {
                        error.printStackTrace();
                        Toast.makeText(this.ctx, "Error al eliminar la tarea", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this.ctx);
        builder.setMessage("Estás seguro de eliminar la tarea?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show();
    }

    public List<Evaluation> getAll() {
        AuthController authController = new AuthController(ctx);
        User user = authController.getUserSession();
        List<EvaluationEntity> evaluationEntityList = evaluationDao.findAll(user.getId());
        List<Evaluation> evaluationList = new ArrayList<>();

        for (EvaluationEntity evaluationEntity : evaluationEntityList) {
            EvaluationMapper mapper = new EvaluationMapper(evaluationEntity);
            Evaluation evaluation = mapper.toBase();
            evaluationList.add(evaluation);
        }

        return evaluationList;
    }

    public List<Evaluation> getRange(Date from, Date to) {
        AuthController authController = new AuthController(ctx);
        User user = authController.getUserSession();
        List<EvaluationEntity> evaluationEntityList = evaluationDao.findByRange(from, to, user.getId());
        List<Evaluation> evaluationList = new ArrayList<>();

        for (EvaluationEntity evaluationEntity : evaluationEntityList) {
            EvaluationMapper mapper = new EvaluationMapper(evaluationEntity);
            Evaluation evaluation = mapper.toBase();
            evaluationList.add(evaluation);
        }

        return evaluationList;
    }

}
