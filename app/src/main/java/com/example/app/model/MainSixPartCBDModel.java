package com.example.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */

public class MainSixPartCBDModel extends RealmObject{
    @PrimaryKey
    private int id;
    String amount;
    String month;


    public MainSixPartCBDModel() {
    }

    public MainSixPartCBDModel(String amount, String month) {
        this.amount = amount;
        this.month = month;
    }

    public MainSixPartCBDModel(int id, String amount, String month) {
        this.id = id;
        this.amount = amount;
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
