package com.example.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.dto.ThirdBudgetModel;

import java.util.List;

public class ThirdBudgetAdapter extends BaseAdapter {
    private Context context;
    private List<ThirdBudgetModel> datas;

    public ThirdBudgetAdapter(Context context, List<ThirdBudgetModel> datas) {
        this.context = context;
        this.datas = datas;
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


        convertView = LayoutInflater.from(context).inflate(R.layout.child_third_budget, null);
        LinearLayout linearLayout = convertView.findViewById(R.id.indicators_layout);

        TextView leimu = convertView.findViewById(R.id.child_leimu);
        TextView leiji = convertView.findViewById(R.id.child_leiji);
        TextView zengzhang = convertView.findViewById(R.id.child_zengzhang);
        TextView pingjiaTitle = convertView.findViewById(R.id.child_pingjia_title);

        leimu.setText(datas.get(position).getLeimu());
        leiji.setText(datas.get(position).getLeiji());
        zengzhang.setText(datas.get(position).getZengzhang());
        pingjiaTitle.setText(datas.get(position).getPaiming());
        if (position % 2 == 0) {
            linearLayout.setBackgroundColor(Color.parseColor("#f6f8fa"));

        }
        if (datas.get(position).getLeimu().equals("静安区")) {
            linearLayout.setBackgroundColor(Color.rgb(216, 243, 254));
        }
        //r216 g243 b254
        return convertView;
    }
//    static class ViewHolder {
//        @BindView(R.id.indicators_layout)
//        LinearLayout linearLayout;
//        @BindView(R.id.child_leimu)
//        TextView leimu;
//        @BindView(R.id.child_leiji) TextView leiji;
//        @BindView(R.id.child_zengzhang) TextView zengzhang;
//        @BindView(R.id.child_pingjia_title) TextView pingjiaTitle;
//        public ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
}
