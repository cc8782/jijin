package com.example.app.db;

import com.example.app.model.Group;
import com.example.app.model.MainMapCompanyNumbersModel;
import com.example.app.model.MainMapModel;

import java.util.List;

import io.realm.Realm;

/**
 * Created by hello on 2018/3/8.
 */

public class GroupDBHelper extends DBHelper {
    public GroupDBHelper(Realm realm) {
        super(realm);
    }
    public Group findGroupByID(int id){
        return  realm
                .where(Group.class)
                .equalTo("id", id)
                .findFirst();
    }
    public List<Group> findAllGroup(){
        return realm.where(Group.class)
                .findAll();
    }
}
