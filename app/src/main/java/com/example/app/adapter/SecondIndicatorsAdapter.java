package com.example.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.dto.SecondIndicatorsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondIndicatorsAdapter extends BaseAdapter {
    private Context context;
    private List<SecondIndicatorsModel> datas;
    public SecondIndicatorsAdapter(Context context, List<SecondIndicatorsModel> datas) {
        this.context=context;
        this.datas=datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.child_second_indicators,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.leimu.setText(datas.get(position).getLeimu());
        viewHolder.leiji.setText(datas.get(position).getLeiji());
        viewHolder.zengzhang.setText(datas.get(position).getZengzhang());
        viewHolder.pingjia.setText(datas.get(position).getPingjia());
        if(position%2==1){
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#f6f8fa"));

        }
        if(position==0){
           viewHolder.pingjia.setVisibility(View.GONE);
            viewHolder.pingjiaTitle.setVisibility(View.VISIBLE);
        }else {
            viewHolder.pingjia.setVisibility(View.VISIBLE);
            viewHolder.pingjiaTitle.setVisibility(View.GONE);
        }
        return convertView;
    }
    static class ViewHolder {
        @BindView(R.id.indicators_layout)
        LinearLayout linearLayout;
        @BindView(R.id.child_leimu)
        TextView leimu;
        @BindView(R.id.child_leiji) TextView leiji;
        @BindView(R.id.child_zengzhang) TextView zengzhang;
        @BindView(R.id.child_pingjia)
        Button pingjia;
        @BindView(R.id.child_pingjia_title) TextView pingjiaTitle;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
