package com.example.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */

public class MainMapCompanyNumbersModel extends RealmObject{
    @PrimaryKey
    private int id;
    String total;
    String zhonghuan;
    String suzhouhe;
    String nanjingxilu;

    public MainMapCompanyNumbersModel(int id, String total, String zhonghuan, String suzhouhe, String nanjingxilu) {
        this.id = id;
        this.total = total;
        this.zhonghuan = zhonghuan;
        this.suzhouhe = suzhouhe;
        this.nanjingxilu = nanjingxilu;
    }

    public MainMapCompanyNumbersModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MainMapCompanyNumbersModel(String total, String zhonghuan, String suzhouhe, String nanjingxilu) {
        this.total = total;
        this.zhonghuan = zhonghuan;
        this.suzhouhe = suzhouhe;
        this.nanjingxilu = nanjingxilu;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getZhonghuan() {
        return zhonghuan;
    }

    public void setZhonghuan(String zhonghuan) {
        this.zhonghuan = zhonghuan;
    }

    public String getSuzhouhe() {
        return suzhouhe;
    }

    public void setSuzhouhe(String suzhouhe) {
        this.suzhouhe = suzhouhe;
    }

    public String getNanjingxilu() {
        return nanjingxilu;
    }

    public void setNanjingxilu(String nanjingxilu) {
        this.nanjingxilu = nanjingxilu;
    }
}
