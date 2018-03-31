package com.example.app.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.app.db.DBHelper;
import com.example.app.model.Jingzhi;
import com.example.app.model.MainSixPartSWLYModel;
import com.example.app.utils.GsonArrayCallback;
import com.example.app.utils.OkHttp3Utils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hello on 2018/3/8.
 */

public class WebDataService {
    private  int flag;

    public  void getMainSixPartModel() {
        Long time=new Date().getTime();
        String str= time.toString().substring(0,12);
        String url_swly = "http://fund.eastmoney.com/Data/Fund_JJJZ_Data.aspx?t=1&lx=1&letter=&gsid=&text=&sort=zdf,desc&page=1,9999&feature=|&dt="+str+"&atfc=&onlySale=0";
        OkHttp3Utils.doGet(url_swly, new GsonArrayCallback<MainSixPartSWLYModel>() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                try {
                    JSONObject jsonObject= JSON.parseObject(data.substring(7));

                 JSONArray arr= JSONArray.parseArray(jsonObject.getString("datas"));
                 List<Jingzhi> jingzhis=new ArrayList<>();
                 for(int i=0;i<arr.size();i++){

                   String ss=arr.get(i).toString();
                   JSONArray jsonArray=JSONArray.parseArray(ss);
                     Jingzhi jingzhi=new Jingzhi(jsonArray.getString(0),jsonArray.getString(1),jsonArray.getString(2),jsonArray.getString(3),jsonArray.getString(4)
                            , jsonArray.getString(5),jsonArray.getString(6),jsonArray.getString(7),jsonArray.getString(8),jsonArray.getString(9)
                             , jsonArray.getString(10),jsonArray.getString(11),jsonArray.getString(12),jsonArray.getString(13),jsonArray.getString(14)
                             , jsonArray.getString(15),jsonArray.getString(16),jsonArray.getString(17),jsonArray.getString(18),jsonArray.getString(19)
                             , jsonArray.getString(20)
                     );

                    jingzhis.add(jingzhi);
                 }
                    DBHelper dbHelper=new DBHelper(DBHelper.getRealm());
                    dbHelper.saveOrUpdate(jingzhis);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }


            @Override
            public void onUi(List<MainSixPartSWLYModel> list) {

            }

            @Override
            public void onFailed(Call call, IOException e) {
                e.toString();
            }
        });


    }

    private void updateData(){
        EventBus.getDefault().postSticky(new UpdateSixPartEvent());
    }
    public class UpdateSixPartEvent {
        private String message;


        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    public class UpdateBudgetEvent {
        private String message;


        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    public class UpdateCenterEvent {
        private String message;


        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    public class UpdateMapEvent {
        private String message;


        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    public class UpdateZhibiaoEvent {
        private String message;


        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
