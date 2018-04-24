package com.example.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.activity.GroupActivity;
import com.example.app.activity.JiaoyiActivity;
import com.example.app.db.DBHelper;
import com.example.app.db.JingZhiDBHelper;
import com.example.app.model.ChiCang;
import com.example.app.model.Group;
import com.example.app.model.Jingzhi;
import com.example.app.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hello on 2018/3/30.
 */

public class GroupListAdapter extends BaseAdapter {
    private Context context;
   Group group;
   JingZhiDBHelper jingZhiDBHelper=new JingZhiDBHelper(DBHelper.getRealm());
    public GroupListAdapter(Context context, Group group){
        this.context=context;
        this.group=group;

    }
    @Override
    public int getCount() {
        return group.getChicang().size();
    }
    public void setData(Group group){
        this.group=group;
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
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.child_group_item,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

       final ChiCang chiCang= group.getChicang().get(position);
            viewHolder.groupItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,JiaoyiActivity.class);
                    intent.putExtra(JiaoyiActivity.JINGZHIDAIMA,chiCang.getDaima());
                    intent.putExtra(GroupActivity.GROUPID,group.getId());
                    context.startActivity(intent);
                }
            });

        Jingzhi nowJingzhi=jingZhiDBHelper.findJingzhiByID(chiCang.getDaima());
        viewHolder.groupDaima.setText(chiCang.getDaima());
        viewHolder.groupName.setText(chiCang.getName());
        viewHolder.groupShuLiang.setText(UiUtils.format2wei(chiCang.getChicangliang().toString()));


        if(chiCang.getDwjz1()!=null){
            viewHolder.groupChengben.setText(UiUtils.format2wei(chiCang.getDwjz1().toString()));
        }

        if(nowJingzhi==null||nowJingzhi.getDwjz1()==null||nowJingzhi.getDwjz1().equals("")){
            viewHolder.groupShizhi.setText("净值有误");
            viewHolder.groupXianjia.setText("净值有误");
            return convertView;
        }
            viewHolder.groupShizhi.setText(UiUtils.format2wei(chiCang.getChicangliang()*Double.parseDouble(nowJingzhi.getDwjz1())));
        if(Double.parseDouble(nowJingzhi.getDwjz1())>=Double.parseDouble(chiCang.getDwjz1())){
            viewHolder.groupyked.setTextColor(ContextCompat.getColor(context, R.color.red));
            viewHolder.groupykbl.setTextColor(ContextCompat.getColor(context, R.color.red));
        }else {
            viewHolder.groupyked.setTextColor(ContextCompat.getColor(context, R.color.green));
            viewHolder.groupykbl.setTextColor(ContextCompat.getColor(context, R.color.green));
        }

        viewHolder.groupXianjia.setText(nowJingzhi.getDwjz1());
        viewHolder.groupyked.setText(UiUtils.format2wei((Double.parseDouble(nowJingzhi.getDwjz1())-Double.parseDouble(chiCang.getDwjz1()))*chiCang.getChicangliang()));
        Double ykbl=(Double.parseDouble(nowJingzhi.getDwjz1())-Double.parseDouble(chiCang.getDwjz1()))/Double.parseDouble(chiCang.getDwjz1())*100.0;
        viewHolder.groupykbl.setText(UiUtils.format2wei(ykbl)+"%");


        return convertView;
    }



    static class ViewHolder {


        @BindView(R.id.group_name) TextView groupName;
        @BindView(R.id.group_daima) TextView groupDaima;
        @BindView(R.id.group_shizhi) TextView groupShizhi;
        @BindView(R.id.group_shuliang) TextView groupShuLiang;
        @BindView(R.id.group_xianjia) TextView groupXianjia;
        @BindView(R.id.group_chengben) TextView groupChengben;
        @BindView(R.id.group_yked) TextView groupyked;
        @BindView(R.id.group_ykbl) TextView groupykbl;
        @BindView(R.id.group_item) LinearLayout groupItem;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
