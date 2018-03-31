package com.example.app.db;

import com.example.app.model.MainMapCompanyNumbersModel;
import com.example.app.model.MainMapModel;

import io.realm.Realm;

/**
 * Created by hello on 2018/3/8.
 */

public class MainMapDBHelper extends DBHelper {
    public MainMapDBHelper(Realm realm) {
        super(realm);
    }
    public MainMapCompanyNumbersModel findCompanies(int id){
        MainMapCompanyNumbersModel mainMapCompanyNumbersModel=new MainMapCompanyNumbersModel();
        mainMapCompanyNumbersModel=realm
                .where(MainMapCompanyNumbersModel.class)
                .equalTo("id", id)
                .findFirst();
        return mainMapCompanyNumbersModel;
    }
    public MainMapModel findCompaniesShui(String gindexId){
        MainMapModel mainMapModel=new MainMapModel();
         mainMapModel= realm
                .where(MainMapModel.class)
                .equalTo("gindexId", gindexId)
                .findFirst();
        return mainMapModel;
    }
    public MainMapModel findCompaniesListView(String nameFlag){
        MainMapModel mainMapModel=new MainMapModel();
        mainMapModel= realm
                .where(MainMapModel.class)
                .equalTo("nameFlag", nameFlag)
                .findFirst();
        return mainMapModel;
    }
}
