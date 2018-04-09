package com.example.app.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */

public class Weituo extends RealmObject {
    @PrimaryKey
    private String id;
    private String groupId;//组合id
    private Date startDate;//提交时间
    private Integer transactionType;//交易类型 0 买；1卖
    private String jingzhiDm;
    private Double buyCash;
    private Double buyshuifei;

    private Double sellnumber;
    private Double sellshuifei;

    private Integer status; //当前状态，0 委托；1 交易 ；2 撤销
    private Date changeStatusDate; //更改状态时间；

    public Weituo() {
    }

    public Double getBuyCash() {
        return buyCash;
    }

    public void setBuyCash(Double buyCash) {
        this.buyCash = buyCash;
    }

    public Double getBuyshuifei() {
        return buyshuifei;
    }

    public void setBuyshuifei(Double buyshuifei) {
        this.buyshuifei = buyshuifei;
    }

    public Double getSellnumber() {
        return sellnumber;
    }

    public void setSellnumber(Double sellnumber) {
        this.sellnumber = sellnumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public String getJingzhiDm() {
        return jingzhiDm;
    }

    public void setJingzhiDm(String jingzhiDm) {
        this.jingzhiDm = jingzhiDm;
    }



    public Double getSellshuifei() {
        return sellshuifei;
    }

    public void setSellshuifei(Double sellshuifei) {
        this.sellshuifei = sellshuifei;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getChangeStatusDate() {
        return changeStatusDate;
    }

    public void setChangeStatusDate(Date changeStatusDate) {
        this.changeStatusDate = changeStatusDate;
    }
}
