package com.example.app.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.app.R;

import butterknife.BindView;

/**
 * Created by hello on 2018/3/5.
 */

public class GroupActivity extends BaseActivity {
    public static String GROUPID="GroupId";
    @BindView(R.id.main_listview)
    ListView listView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        getSupportActionBar().hide();

    }

    private void setData() {

    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
