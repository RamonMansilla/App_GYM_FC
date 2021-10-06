package com.example.app_gymfc.models;

import java.io.Serializable;
import java.util.Date;

public class Evaluation implements Serializable {
    private long id;
    private Date date;
    private double weight;
    private int imc;

    public Evaluation(long id, Date date, double weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public int getImc() {
        return imc;
    }
}
