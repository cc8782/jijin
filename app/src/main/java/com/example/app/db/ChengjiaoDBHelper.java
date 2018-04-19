package com.example.app.db;

import com.example.app.model.Chengjiao;
import com.example.app.model.Weituo;

import java.util.List;

import io.realm.Realm;

/**
 * Created by hello on 2018/3/8.
 */

public class ChengjiaoDBHelper extends DBHelper {
    public ChengjiaoDBHelper(Realm realm) {
        super(realm);
    }

    public List<Chengjiao> findbyGroupId(String groupId){
        return  realm
                .where(Chengjiao.class)
                .equalTo("groupId", groupId)
                .findAll();
    }
    public List<Chengjiao> findAll(){
        return realm.where(Chengjiao.class)
                .findAll();
    }
}
