package com.example.app.db;

import com.example.app.model.MainSixPartAreaModel;
import com.example.app.model.MainSixPartCBDModel;
import com.example.app.model.MainSixPartGDZCModel;
import com.example.app.model.MainSixPartJYZSModel;
import com.example.app.model.MainSixPartSWLYModel;
import com.example.app.model.MainSixPartWDHYModel;

import java.util.List;

import io.realm.Realm;

/**
 * Created by hello on 2018/3/8.
 */

public class MainSixpartDBHelper extends DBHelper {
    public MainSixpartDBHelper(Realm realm) {
        super(realm);
    }
    public MainSixPartSWLYModel findSWLY(int id){
        return realm
                .where(MainSixPartSWLYModel.class)
                .equalTo("id", id)
                .findFirst();
    }
    public List<MainSixPartSWLYModel> findallSWLY(){
        return realm
                .where(MainSixPartSWLYModel.class)
                .findAll();
    }
    public List<MainSixPartAreaModel> findallArea(){
        return realm
                .where(MainSixPartAreaModel.class)
                .findAll();
    }
    public List<MainSixPartGDZCModel> findallGDZC(){
        return realm
                .where(MainSixPartGDZCModel.class)
                .findAll();
    }
    public MainSixPartWDHYModel findWDHY(int id){
        return realm
                .where(MainSixPartWDHYModel.class)
                .equalTo("id", id)
                .findFirst();
    }
    public MainSixPartCBDModel findCBD(int id){
        return realm
                .where(MainSixPartCBDModel.class)
                .equalTo("id", id)
                .findFirst();
    }
    public MainSixPartJYZSModel findJYZS(int id){
        return realm
                .where(MainSixPartJYZSModel.class)
                .equalTo("id", id)
                .findFirst();
    }

}
