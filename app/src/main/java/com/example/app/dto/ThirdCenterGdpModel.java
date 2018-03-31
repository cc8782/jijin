package com.example.app.dto;

/**
 * Created by hello on 2018/3/6.
 */

public class ThirdCenterGdpModel {
    String leimu;
    String leiji;

    String zengzhang;
    String bizhong;
    String paiming;

    public ThirdCenterGdpModel(String leimu, String leiji, String zengzhang, String bizhong, String paiming) {
        this.leimu = leimu;
        this.leiji = leiji;
        this.zengzhang = zengzhang;
        this.bizhong = bizhong;
        this.paiming = paiming;
    }

    public ThirdCenterGdpModel() {
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

    public String getBizhong() {
        return bizhong;
    }

    public void setBizhong(String bizhong) {
        this.bizhong = bizhong;
    }

    public String getPaiming() {
        return paiming;
    }

    public void setPaiming(String paiming) {
        this.paiming = paiming;
    }
}
