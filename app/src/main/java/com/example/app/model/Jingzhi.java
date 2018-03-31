package com.example.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */

public class Jingzhi extends RealmObject {
    @PrimaryKey
    private String daima;
    private String name;
    private String jianpin;
    private String dwjz1;
    private String ljjz1;
    private String dwjz2;
    private String ljjz2;
    private String rzzz;
    private String rzzl;
    private String sgzt;
    private String shzt;
    private String str1;
    private String str2;
    private String str3;
    private String str4;
    private String str5;
    private String str6;
    private String sgfl;
    private String shfl;

    private String str7;
    private String str8;


    public Jingzhi() {
    }

    public Jingzhi(String daima, String name, String jianpin, String dwjz1, String ljjz1, String dwjz2, String ljjz2, String rzzz, String rzzl, String sgzt, String shzt, String str1, String str2, String str3, String str4, String str5, String str6, String sgfl, String shfl, String str7, String str8) {
        this.daima = daima;
        this.name = name;
        this.jianpin = jianpin;
        this.dwjz1 = dwjz1;
        this.ljjz1 = ljjz1;
        this.dwjz2 = dwjz2;
        this.ljjz2 = ljjz2;
        this.rzzz = rzzz;
        this.rzzl = rzzl;
        this.sgzt = sgzt;
        this.shzt = shzt;
        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;
        this.str4 = str4;
        this.str5 = str5;
        this.str6 = str6;
        this.sgfl = sgfl;
        this.shfl = shfl;
        this.str7 = str7;
        this.str8 = str8;
    }

    public String getDaima() {
        return daima;
    }

    public void setDaima(String daima) {
        this.daima = daima;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJianpin() {
        return jianpin;
    }

    public void setJianpin(String jianpin) {
        this.jianpin = jianpin;
    }

    public String getDwjz1() {
        return dwjz1;
    }

    public void setDwjz1(String dwjz1) {
        this.dwjz1 = dwjz1;
    }

    public String getLjjz1() {
        return ljjz1;
    }

    public void setLjjz1(String ljjz1) {
        this.ljjz1 = ljjz1;
    }

    public String getDwjz2() {
        return dwjz2;
    }

    public void setDwjz2(String dwjz2) {
        this.dwjz2 = dwjz2;
    }

    public String getLjjz2() {
        return ljjz2;
    }

    public void setLjjz2(String ljjz2) {
        this.ljjz2 = ljjz2;
    }

    public String getRzzz() {
        return rzzz;
    }

    public void setRzzz(String rzzz) {
        this.rzzz = rzzz;
    }

    public String getRzzl() {
        return rzzl;
    }

    public void setRzzl(String rzzl) {
        this.rzzl = rzzl;
    }

    public String getSgzt() {
        return sgzt;
    }

    public void setSgzt(String sgzt) {
        this.sgzt = sgzt;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }

    public String getStr4() {
        return str4;
    }

    public void setStr4(String str4) {
        this.str4 = str4;
    }

    public String getStr5() {
        return str5;
    }

    public void setStr5(String str5) {
        this.str5 = str5;
    }

    public String getStr6() {
        return str6;
    }

    public void setStr6(String str6) {
        this.str6 = str6;
    }

    public String getSgfl() {
        return sgfl;
    }

    public void setSgfl(String sgfl) {
        this.sgfl = sgfl;
    }

    public String getShfl() {
        return shfl;
    }

    public void setShfl(String shfl) {
        this.shfl = shfl;
    }

    public String getStr7() {
        return str7;
    }

    public void setStr7(String str7) {
        this.str7 = str7;
    }

    public String getStr8() {
        return str8;
    }

    public void setStr8(String str8) {
        this.str8 = str8;
    }
}
