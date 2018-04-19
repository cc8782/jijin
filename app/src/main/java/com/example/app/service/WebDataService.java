package com.example.app.service;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.app.MyApp;
import com.example.app.R;
import com.example.app.cache.Cache;
import com.example.app.db.DBHelper;
import com.example.app.model.Jingzhi;
import com.example.app.utils.GsonObjectCallback;
import com.example.app.utils.NetWorkUtils;
import com.example.app.utils.OkHttp3Utils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hello on 2018/3/8.
 */

public class WebDataService {
    private int flag;

    public void getMainSixPartModel(final Context context) {
        if (!NetWorkUtils.isNetworkConnected(context)) {
            Toast.makeText(context, "网络没有连接。。。", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if(new Date().after(dateFormat.parse("2018-05-01"))){
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (NetWorkUtils.getConnectedType(context) != 1) {
            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
            builder.setMessage("当前不是WIFI连接，是否继续更新数据？");
            builder.setTitle("设置导出Excle名字");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    updatejignzhi(context);
                }
            });

            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }else{
            updatejignzhi(context);  
        }


    }

    private void updatejignzhi(final Context context) {
        Long time = new Date().getTime();
        String str = time.toString().substring(0, 12);
        String url_swly = "http://fund.eastmoney.com/Data/Fund_JJJZ_Data.aspx?t=1&lx=1&letter=&gsid=&text=&sort=zdf,desc&page=1,9999&feature=|&dt=" + str + "&atfc=&onlySale=0";
        OkHttp3Utils.doGet(url_swly, new GsonObjectCallback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                try {
                    JSONObject jsonObject = JSON.parseObject(data.substring(7));

                    JSONArray arr = JSONArray.parseArray(jsonObject.getString("datas"));
                    final List<Jingzhi> jingzhis = new ArrayList<>();
                    String ss1 = arr.get(0).toString();
                    JSONArray jsonArray1 = JSONArray.parseArray(ss1);
                    if (jsonArray1.getString(4).equals("")) {
                        return;
                    }
                    for (int i = 0; i < arr.size(); i++) {

                        String ss = arr.get(i).toString();
                        JSONArray jsonArray = JSONArray.parseArray(ss);
                        Jingzhi jingzhi;

                             jingzhi = new Jingzhi(jsonArray.getString(0), jsonArray.getString(1), jsonArray.getString(2), jsonArray.getString(3), jsonArray.getString(4)
                                    , jsonArray.getString(5), jsonArray.getString(6), jsonArray.getString(7), jsonArray.getString(8), jsonArray.getString(9)
                                    , jsonArray.getString(10), jsonArray.getString(11), jsonArray.getString(12), jsonArray.getString(13), jsonArray.getString(14)
                                    , jsonArray.getString(15), jsonArray.getString(16), jsonArray.getString(17), jsonArray.getString(18), jsonArray.getString(19)
                                    , jsonArray.getString(20)
                            );



                        jingzhis.add(jingzhi);
                    }
                    DBHelper dbHelper = new DBHelper(DBHelper.getRealm());
                    dbHelper.saveOrUpdate(jingzhis);
                    JSONArray dates = JSONArray.parseArray(jsonObject.getString("showday"));
                    final String updateDate;
                    if (dates.size() > 0) {
                        updateDate = dates.get(0).toString();

                        Cache.putupdateDate(updateDate, MyApp.context);
                        Handler handler = OkHttp3Utils.getInstance().getHandler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                onUi(updateDate);

                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onUi(Object o) {
                TradingCenter tradingCenter = new TradingCenter();
                tradingCenter.judementWeituo();
                tradingCenter.judementGroup();
                EventBus.getDefault().postSticky(new UpdateJingzhiEvent());
                Toast.makeText(context, "数据更新到" + o.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(Call call, IOException e) {
                e.toString();
            }
        });
    }

    private void updateData() {
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
    public class UpdateJingzhiEvent {
        private String message;


        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
