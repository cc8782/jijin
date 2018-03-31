package com.example.app.dto;

/**
 * Created by hello on 2018/3/6.
 */

public class SecondIndicatorsModel {
    String leimu;
    String leiji;

    String zengzhang;
    String pingjia;

    public SecondIndicatorsModel(String leimu, String leiji, String zengzhang, String pingjia) {
        this.leimu = leimu;
        this.leiji = leiji;
        this.zengzhang = zengzhang;
        this.pingjia = pingjia;
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

    public String getPingjia() {
        return pingjia;
    }

    public void setPingjia(String pingjia) {
        this.pingjia = pingjia;
    }
}
