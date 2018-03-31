package com.example.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.activity.GroupActivity;
import com.example.app.model.Group;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hello on 2018/3/30.
 */

public class MainListAdapter extends BaseAdapter {
    private Context context;
    List<Group> datas=new ArrayList<>();
    public MainListAdapter(Context context,List<Group> datas){
        this.context=context;
        this.datas=datas;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }else {
            return 1;
        }
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
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.child_main_item,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if(getItemViewType(position)==0){
            viewHolder.zuheName.setText("名称");
            viewHolder.zuheJz.setText("累积净值");
            viewHolder.zuheZdh.setText("最大回撤");
        }else{
            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,GroupActivity.class);
                    intent.putExtra(GroupActivity.GROUPID,datas.get(position).getId());
                    context.startActivity(intent);
                }
            });

            viewHolder.zuheName.setText(datas.get(position).getName()+"");
            viewHolder.zuheJz.setText(datas.get(position).getLjjz()+"");
            viewHolder.zuheZdh.setText(String.valueOf(datas.get(position).getMostLost()*100).substring(0,5)+"%");
        }

        return convertView;
    }



    static class ViewHolder {
        @BindView(R.id.main_item)
        LinearLayout linearLayout;
        @BindView(R.id.child_zuhe_name) TextView zuheName;
        @BindView(R.id.child_zuhe_jz) TextView zuheJz;
        @BindView(R.id.child_zuhe_zdhc) TextView zuheZdh;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
