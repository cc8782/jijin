package com.example.app.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.app.R;
import com.example.app.adapter.MainListAdapter;
import com.example.app.model.Group;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hello on 2018/3/5.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_listview)
    ListView listView;
    private List<Group> datas=new ArrayList<>();
    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        datas.add(new Group("测试号001",1.2101,-0.15));
        datas.add(new Group("测试号002",1.3101,-0.13));
        datas.add(new Group("测试号003",1.2141,-0.34));
        datas.add(new Group("测试号0041",1.2101,-0.24));
        datas.add(new Group("测试号0051",5.2101,-0.14));
        datas.add(new Group("测试号0061",2.2101,-0.5));
        MainListAdapter mainListAdapter=new MainListAdapter(this,datas);
        listView.setAdapter(mainListAdapter);
        mainListAdapter.notifyDataSetChanged();

    }

    private void setData() {

    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
