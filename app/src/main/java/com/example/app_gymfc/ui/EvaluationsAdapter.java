package com.example.app_gymfc.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_gymfc.R;
import com.example.app_gymfc.models.Evaluation;

import java.text.SimpleDateFormat;
import java.util.List;

public class EvaluationsAdapter extends BaseAdapter {
    private Context ctx;
    private List<Evaluation> evaluationList;

    public EvaluationsAdapter(Context ctx, List<Evaluation> evaluationList) {
        this.ctx = ctx;
        this.evaluationList = evaluationList;
    }

    @Override
    public int getCount() {
        return evaluationList.size();
    }

    @Override
    public Object getItem(int i) {
        return evaluationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return evaluationList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);

        view = inflater.inflate(R.layout.item_evaluation, null);

        Evaluation evaluation = evaluationList.get(i);

        TextView tvId = view.findViewById(R.id.item_evaluation_tv_id);
        TextView tvDate = view.findViewById(R.id.item_evaluation_tv_date);
        TextView tvWeight = view.findViewById(R.id.item_evaluation_tv_weight);
        TextView tvImc = view.findViewById(R.id.item_evaluation_tv_imc);


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        tvId.setText(Long.toString(evaluation.getId()));
        tvDate.setText(dateFormat.format(evaluation.getDate()));
        tvWeight.setText(Double.toString(evaluation.getWeight()));
        tvImc.setText(Double.toString(evaluation.getWeight() / (1.70 * 1.70)));
        return view;
    }
}

