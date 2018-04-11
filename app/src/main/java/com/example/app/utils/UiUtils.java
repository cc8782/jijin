package com.example.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.DecimalFormat;

/**
 * Created by hello on 2018/3/7.
 */

public class UiUtils {

    public static String formatdouble(String str) {
        if (null == str || "".equals(str)) {
            return "0.0";
        } else {
            return str;
        }
    }
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };


    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Boolean bigThenZero(String str) {
        if (null == str || "".equals(str)) {
            return false;
        } else {
            try {
                if (Double.parseDouble(str) >= 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static String formatYI(String num) {
        float result = Float.parseFloat(num) / 100000000f;
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(result);
    }

    public static String format1wei(String num) {
        float result = Float.parseFloat(num);
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(result);
    }
    public static String format2wei(String num) {
        float result = Float.parseFloat(num);
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(result);
    }
    public static String format2wei(Double num) {

        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(num);
    }
    public static String format4wei(Double num) {

        DecimalFormat df = new DecimalFormat("0.0000");
        return df.format(num);
    }
    public static Double formatJingzhi(String ss){
        if(ss==null||ss.equals("")||ss.equals("0")||ss.equals("0.00%")){
            return 0.0;
        }

        return Double.parseDouble(ss);

    }
    public static String formatShui(String ss){
        if(ss==null||ss.equals("")||ss.equals("0")||ss.equals("0.00%")){
            return "0";
        }
        String[] number=ss.split("%");
        Double d=0.0;
        try {
            d=  Double.parseDouble(number[0]);
        }catch (Exception e){
            e.printStackTrace();

        }
        return String.valueOf(d/100);

    }
}
