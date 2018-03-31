package com.example.app.dto;

/**
 * Created by hello on 2018/3/6.
 */

public class ThirdBudgetModel {
    String leimu;
    String leiji;

    String zengzhang;
    String paiming;

    public ThirdBudgetModel(String leimu, String leiji, String zengzhang, String paiming) {
        this.leimu = leimu;
        this.leiji = leiji;
        this.zengzhang = zengzhang;
        this.paiming = paiming;
    }

    public String getLeimu() {
        return leimu;
    }

    public void setLeimu(String leimu) {
        this.leimu = leimu;
    }

    public String getLeiji() {
        return leiji;
    }

    public void setLeiji(String leiji) {
        this.leiji = leiji;
    }

    public String getZengzhang() {
        return zengzhang;
    }

    public void setZengzhang(String zengzhang) {
        this.zengzhang = zengzhang;
    }

    public String getPaiming() {
        return paiming;
    }

    public void setPaiming(String paiming) {
        this.paiming = paiming;
    }
}
