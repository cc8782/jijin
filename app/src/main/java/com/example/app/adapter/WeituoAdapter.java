package com.example.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.activity.GroupActivity;
import com.example.app.activity.JiaoyiActivity;
import com.example.app.db.DBHelper;
import com.example.app.db.JingZhiDBHelper;
import com.example.app.model.Jingzhi;
import com.example.app.model.Weituo;
import com.example.app.utils.UiUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hello on 2018/3/30.
 */

public class WeituoAdapter extends BaseAdapter {
    private Context context;
    List<Weituo> datas = new ArrayList<>();
    private String groupid;
    private JingZhiDBHelper jingZhiDBHelper=new JingZhiDBHelper(DBHelper.getRealm());
    public WeituoAdapter(Context context, List<Weituo> datas, String groupid) {
        this.context = context;
        this.datas = datas;
        this.groupid=groupid;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    public void setData(List<Weituo> datas) {
        this.datas = datas;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_weituo_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Weituo weituo = datas.get(position);

        Jingzhi jingzhi=jingZhiDBHelper.findJingzhiByID(weituo.getJingzhiDm());
        if(jingzhi!=null){
            viewHolder.jingzhiName.setText(jingzhi.getName()+ "");
            viewHolder.jingzhiDaima.setText(jingzhi.getDaima() + "");
        }

        if (weituo.getTransactionType()==1) {
            viewHolder.transactionType.setTextColor(ContextCompat.getColor(context, R.color.red));
            viewHolder.transactionType.setText("卖出");
            viewHolder.weituozhi.setTextColor(ContextCompat.getColor(context, R.color.red));
            viewHolder.weituozhi.setText(weituo.getSellnumber()+"份");

        } else {
            viewHolder.transactionType.setTextColor(ContextCompat.getColor(context, R.color.green));
            viewHolder.transactionType.setText("买入");
            viewHolder.weituozhi.setTextColor(ContextCompat.getColor(context, R.color.green));
            viewHolder.weituozhi.setText(weituo.getBuyCash()+"");
        }
        if(weituo.getStatus()==0){
            viewHolder.zhuangtai.setText("委托中");
        }else if(weituo.getStatus()==1){
            viewHolder.zhuangtai.setText("已成交");
        }else{
            viewHolder.zhuangtai.setText("已撤销");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日HH:mm");
       viewHolder.shijian.setText(formatter.format(weituo.getStartDate()).toString());
        return convertView;
    }


    static class ViewHolder {

        @BindView(R.id.jingzhi_name)
        TextView jingzhiName;
        @BindView(R.id.jingzhi_daima)
        TextView jingzhiDaima;
        @BindView(R.id.weituozhi)
        TextView weituozhi;
        @BindView(R.id.transactionType)
        TextView transactionType;
        @BindView(R.id.zhuangtai)
        TextView zhuangtai;
        @BindView(R.id.shijian)
        TextView shijian;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
