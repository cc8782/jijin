package com.example.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */

public class MainSixPartWDHYModel extends RealmObject {
    @PrimaryKey
    private int id;
    private String yearmonth;
    private String wdhyName;
    private String wdhyQyNum;
    private String wdhyTax;
    private String wdhyQjTax;
    private String wdhyTaxZzl;
    private String wdhyTaxZb;

    public MainSixPartWDHYModel() {
    }

    public MainSixPartWDHYModel(int id, String yearmonth, String wdhyName, String wdhyQyNum, String wdhyTax, String wdhyQjTax, String wdhyTaxZzl, String wdhyTaxZb) {
        this.id = id;
        this.yearmonth = yearmonth;
        this.wdhyName = wdhyName;
        this.wdhyQyNum = wdhyQyNum;
        this.wdhyTax = wdhyTax;
        this.wdhyQjTax = wdhyQjTax;
        this.wdhyTaxZzl = wdhyTaxZzl;
        this.wdhyTaxZb = wdhyTaxZb;
    }

    public MainSixPartWDHYModel(String yearmonth, String wdhyName, String wdhyQyNum, String wdhyTax, String wdhyQjTax, String wdhyTaxZzl, String wdhyTaxZb) {
        this.yearmonth = yearmonth;
        this.wdhyName = wdhyName;
        this.wdhyQyNum = wdhyQyNum;
        this.wdhyTax = wdhyTax;
        this.wdhyQjTax = wdhyQjTax;
        this.wdhyTaxZzl = wdhyTaxZzl;
        this.wdhyTaxZb = wdhyTaxZb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYearmonth() {
        return yearmonth;
    }

    public void setYearmonth(String yearmonth) {
        this.yearmonth = yearmonth;
    }

    public String getWdhyName() {
        return wdhyName;
    }

    public void setWdhyName(String wdhyName) {
        this.wdhyName = wdhyName;
    }

    public String getWdhyQyNum() {
        return wdhyQyNum;
    }

    public void setWdhyQyNum(String wdhyQyNum) {
        this.wdhyQyNum = wdhyQyNum;
    }

    public String getWdhyTax() {
        return wdhyTax;
    }

    public void setWdhyTax(String wdhyTax) {
        this.wdhyTax = wdhyTax;
    }

    public String getWdhyQjTax() {
        return wdhyQjTax;
    }

    public void setWdhyQjTax(String wdhyQjTax) {
        this.wdhyQjTax = wdhyQjTax;
    }

    public String getWdhyTaxZzl() {
        return wdhyTaxZzl;
    }

    public void setWdhyTaxZzl(String wdhyTaxZzl) {
        this.wdhyTaxZzl = wdhyTaxZzl;
    }

    public String getWdhyTaxZb() {
        return wdhyTaxZb;
    }

    public void setWdhyTaxZb(String wdhyTaxZb) {
        this.wdhyTaxZb = wdhyTaxZb;
    }
}
