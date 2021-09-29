package com.example.app_gymfc.models;

import java.util.Date;

public class Evaluation {
    private long id;
    private String date;
    private String weight;
    private String imc;

    public Evaluation(String date, String weight, String imc) {
        this.date = date;
        this.weight = weight;
        this.imc = imc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getWeight() {
        return weight;
    }

    public String getImc() {
        return imc;
    }
}
