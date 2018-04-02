package com.example.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app.R;

import java.security.acl.Group;

import butterknife.BindView;

/**
 * Created by hello on 2018/3/5.
 */

public class GroupActivity extends BaseActivity {
    public static String GROUPID="GroupId";
    private String groupId;
    @BindView(R.id.total)
    TextView totalText;
    @BindView(R.id.dayprofits)
    TextView dayprofitsText;
    @BindView(R.id.marketValue)
    TextView marketValueText;
    @BindView(R.id.group_search)
    RelativeLayout relativeLayout;
    @BindView(R.id.cash)
    TextView cashText;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_group;
    }
    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        groupId=getIntent().getStringExtra(GROUPID);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GroupActivity.this,SerachAddActivity.class);
                GroupActivity.this.startActivity(intent);
            }
        });
    }

    private void setData() {

    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
