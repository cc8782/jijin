package com.example.app.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */ 

public class Group extends RealmObject {
    @PrimaryKey
    private String Id;
    private String name;//组合名称
    private String startDate;//开通时间
    private double startValue;//初始市值
    private double totalValue;//总值
    private double cash;//现金
    private double marketValue;//市值
    private double mostLost;//最大回撤
    private double ljjz;
   private RealmList<ChiCang> chicang;
    private int status;//当前状态，0正在使用，1废弃
    private Double profit;//每日收益
    private Date update;
    private Double weituo;

    public Group() {
    }
    public Group(String name,double lijz, double zdhc) {
        this.name=name;
        this.ljjz=lijz;
        this.mostLost=zdhc;
    }

    public Double getWeituo() {
        return weituo;
    }

    public void setWeituo(Double weituo) {
        this.weituo = weituo;
    }

    public double getLjjz() {
        return ljjz;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public void setLjjz(double ljjz) {
        this.ljjz = ljjz;
    }

    public double getMostLost() {
        return mostLost;
    }

    public void setMostLost(double mostLost) {
        this.mostLost = mostLost;
    }



    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public double getStartValue() {
        return startValue;
    }

    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public void setChicang(RealmList<ChiCang> chicang) {
        this.chicang = chicang;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RealmList<ChiCang> getChicang() {
        return chicang;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
