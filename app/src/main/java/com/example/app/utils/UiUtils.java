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
    public static String formatdouble(String str) {
        if (null == str || "".equals(str)) {
            return "0.0";
        } else {
            return str;
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

}
