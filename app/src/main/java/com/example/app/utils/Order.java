package com.example.app.utils;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by hello on 2018/4/9.
 */

public class Order implements Serializable {

    public String id;

    public String restPhone;

    public String restName;

    public String receiverAddr;


    public Order(String id,String restPhone, String restName, String receiverAddr) {
        this.id = id;
        this.restPhone = restPhone;
        this.restName = restName;

        this.receiverAddr = receiverAddr;
    }

    public Order(JSONObject obj) {
        this.id = obj.optString("order_number");
        this.restPhone = obj.optString("Rphone");
        this.restName = obj.optString("Rname");
        this.receiverAddr = obj.optString("receiver_address");
    }
}