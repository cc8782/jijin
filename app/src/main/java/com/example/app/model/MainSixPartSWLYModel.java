package com.example.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */

public class MainSixPartSWLYModel extends RealmObject{
    @PrimaryKey
    private int id;
    String YEAR;
    String MONTH;
    String VALUE;

    public MainSixPartSWLYModel(int id, String YEAR, String MONTH, String VALUE) {
        this.id = id;
        this.YEAR = YEAR;
        this.MONTH = MONTH;
        this.VALUE = VALUE;
    }

    public MainSixPartSWLYModel(String YEAR, String MONTH, String VALUE) {
        this.YEAR = YEAR;
        this.MONTH = MONTH;
        this.VALUE = VALUE;
    }

    public MainSixPartSWLYModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String VALUE) {
        this.VALUE = VALUE;
    }
}
