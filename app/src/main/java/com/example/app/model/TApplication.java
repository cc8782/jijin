package com.example.app.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hello on 2018/3/8.
 */ 

public class TApplication extends RealmObject {
    @PrimaryKey
    private String Id;
    private String group;//所属组合
    private Date startDate;//申请日期
    private Date startTime;//申请时间
    private double money;
    private String daima;
    private int status;//0未成交，1已成交


}
