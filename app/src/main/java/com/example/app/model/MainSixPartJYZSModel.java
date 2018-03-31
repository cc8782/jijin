package com.example.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */

public class MainSixPartJYZSModel extends RealmObject{
    @PrimaryKey
    private int id;

    String MONTH;
    String JYZS;

    public MainSixPartJYZSModel(int id, String MONTH, String JYZS) {
        this.id = id;
        this.MONTH = MONTH;
        this.JYZS = JYZS;
    }

    public MainSixPartJYZSModel(String MONTH, String JYZS) {
        this.MONTH = MONTH;
        this.JYZS = JYZS;
    }

    public MainSixPartJYZSModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public String getJYZS() {
        return JYZS;
    }

    public void setJYZS(String JYZS) {
        this.JYZS = JYZS;
    }
}
