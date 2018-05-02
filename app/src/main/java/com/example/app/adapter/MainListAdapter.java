package com.example.app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import com.example.app.db.DBHelper;
import com.example.app.model.Group;
import com.example.app.utils.UiUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

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
    public void setData(List<Group> datas){
        this.datas=datas;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.child_main_item,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
           final Group group=datas.get(position);
            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,GroupActivity.class);
                    intent.putExtra(GroupActivity.GROUPID,group.getId());
                    context.startActivity(intent);
                }
            });
        viewHolder.linearLayout.setOnLongClickListener(new MyOnLongClickListener(position));
            viewHolder.zuheName.setText(group.getName()+"");
            if(datas.get(position).getLjjz()>0){
            viewHolder.relativeLayout.setBackground(AppCompatResources.getDrawable(context,R.drawable.cornerbluelight));
            }else {
                viewHolder.relativeLayout.setBackground(AppCompatResources.getDrawable(context,R.drawable.cornerblue));
            }
            viewHolder.zuheDate.setText("创建于"+group.getStartDate());
            viewHolder.ljjz.setText(UiUtils.format2wei(group.getLjjz()));
            viewHolder.mostlost.setText(UiUtils.format2wei(group.getMostLost()*100)+"%");


        return convertView;
    }



    static class ViewHolder {
        @BindView(R.id.main_item)
        LinearLayout linearLayout;

        @BindView(R.id.child_zuhe_name) TextView zuheName;
        @BindView(R.id.child_zuhe_date) TextView zuheDate;
        @BindView(R.id.main_round)
        RelativeLayout relativeLayout;
        @BindView(R.id.main_ljjz) TextView ljjz;
        @BindView(R.id.child_zuhe_mostlost) TextView mostlost;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
   class MyOnLongClickListener implements View.OnLongClickListener {

        private int position;
        public MyOnLongClickListener(int position){

            this.position=position;
        }
       @Override
       public boolean onLongClick(View v) {
           AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("删除此组合?");
           builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int id) {
                 Group group=datas.get(position);
               Realm realm= DBHelper.getRealm();
               realm.beginTransaction();
               group.setStatus(1);
               realm.commitTransaction();
                   EventBus.getDefault().postSticky(new UpdateGroupEvent());
               }
           });

           builder.setCancelable(false);
           builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                  dialog.dismiss();
               }
           });
           builder.show();
           return false;
       }
   }
    public class UpdateGroupEvent {
        private String message;


        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
