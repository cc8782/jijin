package com.example.app.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.db.DBHelper;
import com.example.app.db.JingZhiDBHelper;
import com.example.app.model.Chengjiao;
import com.example.app.model.Jingzhi;
import com.example.app.model.Weituo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hello on 2018/3/30.
 */

public class ChengjiaoAdapter extends BaseAdapter {
    private Context context;
    List<Chengjiao> datas = new ArrayList<>();
    private String groupid;

    public ChengjiaoAdapter(Context context, List<Chengjiao> datas, String groupid) {
        this.context = context;
        this.datas = datas;
        this.groupid=groupid;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    public void setData(List<Chengjiao> datas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.child_chengjiao_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Chengjiao chengjiao = datas.get(position);



            viewHolder.jingzhiName.setText(chengjiao.getName()+ "");
            viewHolder.jingzhiDaima.setText(chengjiao.getDaima() + "");


        if (chengjiao.getTransactionType()==1) {
            viewHolder.transactionType.setTextColor(ContextCompat.getColor(context, R.color.red));
            viewHolder.transactionType.setText("卖出");
            viewHolder.weituozhi.setTextColor(ContextCompat.getColor(context, R.color.red));
            viewHolder.weituozhi.setText(chengjiao.getSellnumber()+"份");

        } else {
            viewHolder.transactionType.setTextColor(ContextCompat.getColor(context, R.color.green));
            viewHolder.transactionType.setText("买入");
            viewHolder.weituozhi.setTextColor(ContextCompat.getColor(context, R.color.green));
            viewHolder.weituozhi.setText(chengjiao.getBuyCash()+"");
        }

            viewHolder.zhuangtai.setText(chengjiao.getDwjz1()+"");


        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日HH:mm");
       viewHolder.shijian.setText(formatter.format(chengjiao.getTradeTime()).toString());
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
