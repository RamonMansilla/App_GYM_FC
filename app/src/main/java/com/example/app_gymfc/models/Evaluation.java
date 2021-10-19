package com.example.app_gymfc.models;

import java.io.Serializable;
import java.util.Date;

public class Evaluation implements Serializable, IEvaluation {
    private long id;
    private Date date;
    private double weight;
    private long userId;

    public Evaluation(Date date, double weight, long userId) {
        this.date = date;
        this.weight = weight;
        this.userId = userId;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public long getUserId() {
        return userId;
    }
}
