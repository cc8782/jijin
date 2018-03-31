package com.example.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */

public class MainMapModel extends RealmObject {
    private String id;
    private String dataValue;
    private String statYear;
    private String statMonth;
    private String statDay;
    private String reportId;
    @PrimaryKey
    private String nameFlag;

    private String gindexId;
    private String eindexId;
    private String rindexId;

    public MainMapModel(String id, String dataValue, String statYear, String statMonth, String statDay, String reportId, String nameFlag, String gindexId, String eindexId, String rindexId) {
        this.id = id;
        this.dataValue = dataValue;
        this.statYear = statYear;
        this.statMonth = statMonth;
        this.statDay = statDay;
        this.reportId = reportId;
        this.nameFlag = nameFlag;
        this.gindexId = gindexId;
        this.eindexId = eindexId;
        this.rindexId = rindexId;
    }

    public MainMapModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getStatYear() {
        return statYear;
    }

    public void setStatYear(String statYear) {
        this.statYear = statYear;
    }

    public String getStatMonth() {
        return statMonth;
    }

    public void setStatMonth(String statMonth) {
        this.statMonth = statMonth;
    }

    public String getStatDay() {
        return statDay;
    }

    public void setStatDay(String statDay) {
        this.statDay = statDay;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getNameFlag() {
        return nameFlag;
    }

    public void setNameFlag(String nameFlag) {
        this.nameFlag = nameFlag;
    }

    public String getGindexId() {
        return gindexId;
    }

    public void setGindexId(String gindexId) {
        this.gindexId = gindexId;
    }

    public String getEindexId() {
        return eindexId;
    }

    public void setEindexId(String eindexId) {
        this.eindexId = eindexId;
    }

    public String getRindexId() {
        return rindexId;
    }

    public void setRindexId(String rindexId) {
        this.rindexId = rindexId;
    }
}
