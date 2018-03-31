package com.example.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.dto.MainSixPartDto;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainSixPartTableAdapter extends BaseAdapter {
    private Context context;
    private List<MainSixPartDto> datas;
    public MainSixPartTableAdapter(Context context, List<MainSixPartDto> datas) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.child_main_sixpart,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.part.setText(datas.get(position).getPart());
        viewHolder.name.setText(datas.get(position).getTitle());
        viewHolder.value.setText(datas.get(position).getValue());
        return convertView;
    }
    static class ViewHolder {
        @BindView(R.id.child_six_part)
        TextView part;
        @BindView(R.id.child_six_name) TextView name;
        @BindView(R.id.child_six_value) TextView value;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
