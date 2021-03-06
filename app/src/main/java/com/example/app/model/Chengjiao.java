package com.example.app.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */

public class Chengjiao extends RealmObject {
    @PrimaryKey
    private String id;
    private String weiTuoId;//委托ID
    private String groupId;//组合id
    private Date tradeTime;//交易时间
    private Integer transactionType;//交易类型 0 买；1卖
    private double buyCash;
    private Jingzhi jingzhi;
private String name;
private String daima;
private String dwjz1;
    private double buyshuifei;
    private Double buyNumber;
    private double buyshengyuCash; //交易结余

    private double sellZongjia;
    private Double sellnumber;
    private double sellshuifei;

    public Chengjiao() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDaima() {
        return daima;
    }

    public void setDaima(String daima) {
        this.daima = daima;
    }

    public String getDwjz1() {
        return dwjz1;
    }

    public void setDwjz1(String dwjz1) {
        this.dwjz1 = dwjz1;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeiTuoId() {
        return weiTuoId;
    }

    public void setWeiTuoId(String weiTuoId) {
        this.weiTuoId = weiTuoId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public double getBuyCash() {
        return buyCash;
    }

    public void setBuyCash(double buyCash) {
        this.buyCash = buyCash;
    }

    public Jingzhi getJingzhi() {
        return jingzhi;
    }


    public double getBuyshuifei() {
        return buyshuifei;
    }

    public void setBuyshuifei(double buyshuifei) {
        this.buyshuifei = buyshuifei;
    }

    public Double getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Double buyNumber) {
        this.buyNumber = buyNumber;
    }

    public double getBuyshengyuCash() {
        return buyshengyuCash;
    }

    public void setBuyshengyuCash(double buyshengyuCash) {
        this.buyshengyuCash = buyshengyuCash;
    }

    public double getSellZongjia() {
        return sellZongjia;
    }

    public void setSellZongjia(double sellZongjia) {
        this.sellZongjia = sellZongjia;
    }

    public Double getSellnumber() {
        return sellnumber;
    }

    public void setSellnumber(Double sellnumber) {
        this.sellnumber = sellnumber;
    }

    public double getSellshuifei() {
        return sellshuifei;
    }

    public void setSellshuifei(double sellshuifei) {
        this.sellshuifei = sellshuifei;
    }
}
