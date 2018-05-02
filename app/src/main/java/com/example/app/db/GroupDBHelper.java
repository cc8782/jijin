package com.example.app.db;

import com.example.app.model.Group;

import java.util.List;

import io.realm.Realm;

/**
 * Created by hello on 2018/3/8.
 */

public class GroupDBHelper extends DBHelper {
    public GroupDBHelper(Realm realm) {
        super(realm);
    }
    public Group findGroupByID(String id){
        return  realm
                .where(Group.class)
                .equalTo("Id", id)
                .findFirst();
    }
    public List<Group> findAllGroup(){
        return realm.where(Group.class)
                .equalTo("status",0)
                .findAll();
    }
}
