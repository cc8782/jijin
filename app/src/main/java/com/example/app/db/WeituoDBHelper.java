package com.example.app.db;

import com.example.app.model.Weituo;

import java.util.List;

import io.realm.Realm;

/**
 * Created by hello on 2018/3/8.
 */

public class WeituoDBHelper extends DBHelper {
    public WeituoDBHelper(Realm realm) {
        super(realm);
    }
    public List<Weituo> findbyStatus(Integer status){
        return  realm
                .where(Weituo.class)
                .equalTo("status", status)
                .findAll();
    }
    public List<Weituo> findbyGroupId(String groupId){
        return  realm
                .where(Weituo.class)
                .equalTo("groupId", groupId)
                .findAll();
    }
    public List<Weituo> findAll(){
        return realm.where(Weituo.class)
                .findAll();
    }
}
