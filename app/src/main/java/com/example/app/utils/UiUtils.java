package com.example.app.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.DecimalFormat;

/**
 * Created by hello on 2018/3/7.
 */

public class UiUtils {

        public static void setListViewHeightBasedOnChildren(ListView listView) {
            if(listView == null) return;
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
    public static String formatYI(String num){
        float result= Float.parseFloat(num)/100000000f;
        DecimalFormat df = new DecimalFormat("0.00");
        return  df.format(result);
    }
    public static String format1wei(String num){
        float result= Float.parseFloat(num);
        DecimalFormat df = new DecimalFormat("0.0");
        return  df.format(result);
    }

}
