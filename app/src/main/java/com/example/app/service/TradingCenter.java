package com.example.app.service;

import android.content.Context;
import android.widget.Toast;

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
    public void judementWeituo(Context context) {
        WeituoDBHelper weituoDBHelper = new WeituoDBHelper(DBHelper.getRealm());
        List<Weituo> weituos = weituoDBHelper.findbyStatus(0);
        if (weituos.size()==0){
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
        String updatedate = Cache.getupdateDate(MyApp.context);
        if (updatedate.equals("")) {
            return;
        }
        Realm realm=DBHelper.getRealm();
        try {
            for (Weituo weituo : weituos) {
                int hour = Integer.parseInt(hourFormat.format(weituo.getStartDate()));
                Calendar cd1 = Calendar.getInstance();
                Calendar cd2 = Calendar.getInstance();
                cd2.setTime(dateFormat.parse(updatedate));
                cd1.setTime(weituo.getStartDate());
                SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                if (hour > 15) {
                    cd1.add(Calendar.DATE, 1);
                }
                if (fmt.format(cd1.getTime()).equals(fmt.format(cd2.getTime()))||cd1.before(cd2)) {
                    trad(weituo ,realm ,context);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(realm!=null){
            realm.close();
        }

    }
    private void trad(Weituo weituo,Realm realm,Context context){
        Group group=groupDBHelper.findGroupByID(weituo.getGroupId());
        Jingzhi jingzhi=jingZhiDBHelper.findJingzhiByID(weituo.getJingzhiDm());

        try {
            Double.parseDouble(jingzhi.getDwjz1());
            Chengjiao chengjiao=new Chengjiao();
            chengjiao.setId(new Date().toString());
            chengjiao.setWeiTuoId(weituo.getId());
            chengjiao.setGroupId(weituo.getGroupId());
            chengjiao.setTradeTime(new Date());
            chengjiao.setDaima(jingzhi.getDaima());
            chengjiao.setName(jingzhi.getName());
            chengjiao.setDwjz1(jingzhi.getDwjz1());
            if(weituo.getTransactionType()==0){
                chengjiao.setTransactionType(weituo.getTransactionType());
                Double number=(weituo.getBuyCash()/UiUtils.formatJingzhi(jingzhi.getDwjz1(),context))/(weituo.getBuyshuifei()+1);
                chengjiao.setBuyshuifei(weituo.getBuyCash()-number*UiUtils.formatJingzhi(jingzhi.getDwjz1(),context));
                chengjiao.setBuyCash(weituo.getBuyCash());
                chengjiao.setBuyNumber(number);


                realm.beginTransaction();
                group.setMarketValue(group.getMarketValue()+number*UiUtils.formatJingzhi(jingzhi.getDwjz1(),context));
                RealmList<ChiCang> chiCangs=group.getChicang();
                int flag=0;
                for(ChiCang chiCang:chiCangs){
                    if(chiCang.getDaima().equals(jingzhi.getDaima())){

                        chiCang.setDwjz1(String.valueOf((UiUtils.formatJingzhi(jingzhi.getDwjz1(),context)*chiCang.getChicangliang()+number*UiUtils.formatJingzhi(jingzhi.getDwjz1(),context))/(chiCang.getChicangliang()+number)));

                        chiCang.setChicangliang(chiCang.getChicangliang()+number);
                        flag=1;
                    }
                }
                if(flag==0){
                    ChiCang chiCang=new ChiCang();
                    chiCang.setDaima(jingzhi.getDaima());
                    chiCang.setName(jingzhi.getName());
                    chiCang.setDwjz1(jingzhi.getDwjz1());

                    chiCang.setChicangliang(number);
                    chiCangs.add(chiCang);
                }
                group.setChicang(chiCangs);
                group.setUpdate(new Date());
                group.setWeituo(group.getWeituo()-weituo.getBuyCash());
                realm.commitTransaction();

            }else if(weituo.getTransactionType()==1){
                chengjiao.setTransactionType(weituo.getTransactionType());
                chengjiao.setSellZongjia(weituo.getSellnumber()*UiUtils.formatJingzhi(jingzhi.getDwjz1(),context));
                chengjiao.setSellnumber(weituo.getSellnumber());
                chengjiao.setSellshuifei(chengjiao.getSellZongjia()*weituo.getSellshuifei());
                realm.beginTransaction();
                group.setCash(group.getCash()+chengjiao.getSellZongjia()-chengjiao.getSellshuifei());
                group.setMarketValue(group.getMarketValue()-chengjiao.getSellZongjia());
                RealmList<ChiCang> chiCangs=group.getChicang();
                for(ChiCang chiCang:chiCangs){
                    if(chiCang.getDaima().equals(jingzhi.getDaima())){
                        if(chiCang.getChicangliang()==chengjiao.getSellnumber()){
                            chiCangs.remove(chiCang);
                            return;
                        }

                        chiCang.setDwjz1(String.valueOf((UiUtils.formatJingzhi(chiCang.getDwjz1(),context)*chiCang.getChicangliang()-chengjiao.getSellZongjia())/(chiCang.getChicangliang()-chengjiao.getSellnumber())));

                        chiCang.setChicangliang(chiCang.getChicangliang()-chengjiao.getSellnumber());
                    }
                }
                group.setChicang(chiCangs);
                group.setUpdate(new Date());
                realm.commitTransaction();
            }
            realm.beginTransaction();
            weituo.setStatus(1);
            realm.commitTransaction();
            DBHelper dbHelper=new DBHelper(DBHelper.getRealm());
            dbHelper.saveOrUpdate(chengjiao);
            dbHelper.saveOrUpdate(weituo);

        }catch (Exception e){
            Toast.makeText(MyApp.context,jingzhi.getDwjz1()+"单位净值出错",Toast.LENGTH_SHORT).show();
            return;
        }



    }
    public void judementGroup(){
        List<Group> groups=groupDBHelper.findAllGroup();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(Cache.getupdateDate(MyApp.context).equals("")){
            return;
        }
        if(groups==null||groups.size()==0){
            return;
        }
        try {
          Date updateDate=  dateFormat.parse(Cache.getupdateDate(MyApp.context));
            if(groups.get(0).getUpdate()==null||groups.get(0).getUpdate().before(updateDate)){
              updateGroupValue(groups,updateDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void updateGroupValue(List<Group> groups, Date updateDate) {
        Realm realm=DBHelper.getRealm();
        JingZhiDBHelper jingZhiDBHelper=new JingZhiDBHelper(DBHelper.getRealm());

        for(Group group:groups){
           Double marketValuetmp=0.0;
           for(ChiCang chiCang:group.getChicang()){
               Jingzhi jingzhi=jingZhiDBHelper.findJingzhiByID(chiCang.getDaima());
               if(jingzhi.getDwjz1()==null||jingzhi.getDwjz1().equals("")){
                   marketValuetmp=marketValuetmp+chiCang.getChicangliang()*Double.parseDouble(chiCang.getDwjz1());
               }else {
                   marketValuetmp=marketValuetmp+chiCang.getChicangliang()*Double.parseDouble(jingzhi.getDwjz1());
               }

           }
           Double totalValuetmp=marketValuetmp+group.getCash()+group.getWeituo();
            Double profittmp=(totalValuetmp-group.getTotalValue())/group.getTotalValue();
           realm.beginTransaction();
            group.setMarketValue(marketValuetmp);
            group.setTotalValue(totalValuetmp);
            group.setUpdate(updateDate);
            if(profittmp<group.getMostLost()){
                group.setMostLost(profittmp);
            }
            group.setProfit(profittmp);
            group.setLjjz(totalValuetmp/group.getStartValue());
            realm.commitTransaction();

        }
        if(realm!=null){
            realm.close();
        }
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
