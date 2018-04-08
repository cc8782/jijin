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
import com.example.app.model.Jingzhi;
import com.example.app.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hello on 2018/3/30.
 */

public class SearchJingzhiAdapter extends BaseAdapter {
    private Context context;
    List<Jingzhi> datas = new ArrayList<>();
    private String groupid;
    public SearchJingzhiAdapter(Context context, List<Jingzhi> datas,String groupid) {
        this.context = context;
        this.datas = datas;
        this.groupid=groupid;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    public void setData(List<Jingzhi> datas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.child_jingzhi_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Jingzhi jingzhi = datas.get(position);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(context,JiaoyiActivity.class);
                    intent.putExtra(JiaoyiActivity.JINGZHIDAIMA,jingzhi.getDaima());
                    intent.putExtra(GroupActivity.GROUPID,groupid);
                    context.startActivity(intent);
                ((Activity) context).finish();
            }
        });

        viewHolder.jingzhiName.setText(jingzhi.getName() + "");
        viewHolder.jingzhiDaima.setText(jingzhi.getDaima() + "");
        if (UiUtils.bigThenZero(jingzhi.getRzzl())) {
            viewHolder.jingzhiRzzl.setTextColor(ContextCompat.getColor(context, R.color.red));
            viewHolder.jingzhiRzzz.setTextColor(ContextCompat.getColor(context, R.color.red));
        } else {
            viewHolder.jingzhiRzzl.setTextColor(ContextCompat.getColor(context, R.color.green));
            viewHolder.jingzhiRzzz.setTextColor(ContextCompat.getColor(context, R.color.green));
        }
        viewHolder.jingzhiDwjz.setText(UiUtils.formatdouble(jingzhi.getDwjz1()));
        viewHolder.jingzhiLjjz.setText(UiUtils.formatdouble(jingzhi.getLjjz1()));
        viewHolder.jingzhiRzzz.setText(UiUtils.formatdouble(jingzhi.getRzzz()));
        viewHolder.jingzhiRzzl.setText(UiUtils.formatdouble(jingzhi.getRzzl()));


        return convertView;
    }


    static class ViewHolder {

        @BindView(R.id.jingzhi_name)
        TextView jingzhiName;
        @BindView(R.id.jingzhi_daima)
        TextView jingzhiDaima;
        @BindView(R.id.jingzhi_ljjz1)
        TextView jingzhiLjjz;
        @BindView(R.id.jignzhi_dwjz1)
        TextView jingzhiDwjz;
        @BindView(R.id.jingzhi_rzzz)
        TextView jingzhiRzzz;
        @BindView(R.id.jingzhi_rzzl)
        TextView jingzhiRzzl;
        @BindView(R.id.jingzhi_item)
        LinearLayout linearLayout;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
