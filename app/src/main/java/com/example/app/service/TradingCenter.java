package com.example.app.service;

import com.example.app.MyApp;
import com.example.app.cache.Cache;
import com.example.app.db.DBHelper;
import com.example.app.db.GroupDBHelper;
import com.example.app.db.JingZhiDBHelper;
import com.example.app.db.WeituoDBHelper;
import com.example.app.model.Chengjiao;
import com.example.app.model.ChiCang;
import com.example.app.model.Group;
import com.example.app.model.Jingzhi;
import com.example.app.model.Weituo;
import com.example.app.utils.UiUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by hello on 2018/4/9.
 */

public class TradingCenter {
    private GroupDBHelper groupDBHelper=new GroupDBHelper(DBHelper.getRealm());
    private JingZhiDBHelper jingZhiDBHelper=new JingZhiDBHelper(DBHelper.getRealm());
    public void judement() {
        WeituoDBHelper weituoDBHelper = new WeituoDBHelper(DBHelper.getRealm());
        List<Weituo> weituos = weituoDBHelper.findbyStatus(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
        String updatedate = Cache.getupdateDate(MyApp.context);
        if (updatedate.equals("")) {
            return;
        }
        try {
            for (Weituo weituo : weituos) {
                int hour = Integer.parseInt(hourFormat.format(weituo.getStartDate()));
                Calendar cd1 = Calendar.getInstance();
                Calendar cd2 = Calendar.getInstance();
                cd2.setTime(dateFormat.parse(updatedate));
                cd1.setTime(weituo.getStartDate());
                if (hour > 15) {
                    cd1.add(Calendar.DATE, 1);
                }
                if (cd1.after(cd2)) {
                    trad(weituo);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void trad(Weituo weituo){
        Group group=groupDBHelper.findGroupByID(weituo.getGroupId());
        Jingzhi jingzhi=jingZhiDBHelper.findJingzhiByID(weituo.getJingzhiDm());
        Realm realm=DBHelper.getRealm();
        Chengjiao chengjiao=new Chengjiao();
        chengjiao.setId(new Date().toString());
        chengjiao.setWeiTuoId(weituo.getId());
        chengjiao.setGroupId(weituo.getGroupId());
        chengjiao.setTradeTime(new Date());
        chengjiao.setJingzhi(jingzhi);
        if(weituo.getTransactionType()==0){
            chengjiao.setTransactionType(weituo.getTransactionType());
            Double number=(weituo.getBuyCash()/Double.parseDouble(UiUtils.formatShui(jingzhi.getDwjz1())))/(weituo.getBuyshuifei()+1);
            chengjiao.setBuyshuifei(weituo.getBuyCash()-number*Double.parseDouble(UiUtils.formatShui(jingzhi.getDwjz1())));
            chengjiao.setBuyCash(weituo.getBuyCash());
            chengjiao.setBuyNumber(number);
            chengjiao.setBuyCash(weituo.getBuyshuifei());

            realm.beginTransaction();
            group.setMarketValue(group.getMarketValue()+number*Double.parseDouble(UiUtils.formatShui(jingzhi.getDwjz1())));
            RealmList<ChiCang> chiCangs=group.getChicang();
            int flag=0;
            for(ChiCang chiCang:chiCangs){
                if(chiCang.getJingzhi().getDaima().equals(jingzhi.getDaima())){
                    Jingzhi oldJingzhi=chiCang.getJingzhi();
                    oldJingzhi.setDwjz1(String.valueOf((Double.parseDouble(UiUtils.formatShui(jingzhi.getDwjz1()))*chiCang.getChicangliang()+number*Double.parseDouble(UiUtils.formatShui(jingzhi.getDwjz1())))/(chiCang.getChicangliang()+number)));
                   chiCang.setJingzhi(oldJingzhi);
                    chiCang.setChicangliang(chiCang.getChicangliang()+number);
                    flag=1;
                }
            }
            if(flag==0){
                ChiCang chiCang=new ChiCang();
                chiCang.setJingzhi(jingzhi);
                chiCang.setChicangliang(number);
                chiCangs.add(chiCang);
            }
            group.setChicang(chiCangs);
            group.setUpdate(new Date());
            realm.commitTransaction();

        }else if(weituo.getTransactionType()==1){
            chengjiao.setTransactionType(weituo.getTransactionType());
            chengjiao.setSellZongjia(weituo.getSellnumber()*Double.parseDouble(UiUtils.formatShui(jingzhi.getDwjz1())));
            chengjiao.setSellnumber(weituo.getSellnumber());
            chengjiao.setSellshuifei(chengjiao.getSellZongjia()*weituo.getSellshuifei());
            realm.beginTransaction();
            group.setCash(group.getCash()+chengjiao.getSellZongjia()-chengjiao.getSellshuifei());
            group.setMarketValue(group.getMarketValue()-chengjiao.getSellZongjia());
            RealmList<ChiCang> chiCangs=group.getChicang();
            for(ChiCang chiCang:chiCangs){
                if(chiCang.getJingzhi().getDaima().equals(jingzhi.getDaima())){
                    if(chiCang.getChicangliang()==chengjiao.getSellnumber()){
                        chiCangs.remove(chiCang);
                        return;
                    }
                    Jingzhi oldJingzhi=chiCang.getJingzhi();
                    oldJingzhi.setDwjz1(String.valueOf((Double.parseDouble(UiUtils.formatShui(oldJingzhi.getDwjz1()))*chiCang.getChicangliang()-chengjiao.getSellZongjia())/(chiCang.getChicangliang()-chengjiao.getSellnumber())));
                    chiCang.setJingzhi(oldJingzhi);
                    chiCang.setChicangliang(chiCang.getChicangliang()-chengjiao.getSellnumber());
                }
            }
            group.setChicang(chiCangs);
            group.setUpdate(new Date());
            realm.commitTransaction();
        }

        DBHelper dbHelper=new DBHelper(DBHelper.getRealm());
        dbHelper.saveOrUpdate(chengjiao);
    }
    public static String addDay(Date d, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar cd = Calendar.getInstance();
            cd.setTime(d);
            cd.add(Calendar.DATE, n);//增加一天
            //cd.add(Calendar.MONTH, n);//增加一个月

            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }
    }
}
