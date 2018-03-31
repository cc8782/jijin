package com.example.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */

public class MainSixPartAreaModel extends RealmObject{
    @PrimaryKey
    private int id;
    String dataValue;
    String statMonth;

    public MainSixPartAreaModel() {
    }

    public MainSixPartAreaModel(int id, String dataValue, String statMonth) {
        this.id = id;
        this.dataValue = dataValue;
        this.statMonth = statMonth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getStatMonth() {
        return statMonth;
    }

    public void setStatMonth(String statMonth) {
        this.statMonth = statMonth;
    }
}
