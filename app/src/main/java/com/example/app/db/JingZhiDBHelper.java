package com.example.app.db;

import com.example.app.model.Jingzhi;

import java.util.List;

import io.realm.Realm;

/**
 * Created by hello on 2018/3/8.
 */

public class JingZhiDBHelper extends DBHelper {
    public JingZhiDBHelper(Realm realm) {
        super(realm);
    }
    public Jingzhi findJingzhiByID(String daima){
        return  realm
                .where(Jingzhi.class)
                .equalTo("daima", daima)
                .findFirst();
    }
    public List<Jingzhi> serachJingzhi(String mohu){
        return realm.where(Jingzhi.class)
                .contains("daima",mohu)
                .contains("jianpin",mohu)
                .findAll();

    }
}
