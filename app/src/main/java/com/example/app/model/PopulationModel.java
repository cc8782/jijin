package com.example.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */

public class PopulationModel extends RealmObject {
    private String count;
    private String genindiIndent;
    @PrimaryKey
    private String id;
    private String increase;
    private String label;
    private String levelId;
    private String name;
    private String param;
    private String percent;
    private String proportion;
    private String seriesName;
    private String value;
    private String year;

    public PopulationModel(String count, String genindiIndent, String id, String increase, String label, String levelId, String name, String param, String percent, String proportion, String seriesName, String value, String year) {
        this.count = count;
        this.genindiIndent = genindiIndent;
        this.id = id;
        this.increase = increase;
        this.label = label;
        this.levelId = levelId;
        this.name = name;
        this.param = param;
        this.percent = percent;
        this.proportion = proportion;
        this.seriesName = seriesName;
        this.value = value;
        this.year = year;
    }

    public PopulationModel() {
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getGenindiIndent() {
        return genindiIndent;
    }

    public void setGenindiIndent(String genindiIndent) {
        this.genindiIndent = genindiIndent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncrease() {
        return increase;
    }

    public void setIncrease(String increase) {
        this.increase = increase;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
