package com.example.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.adapter.GroupListAdapter;
import com.example.app.db.DBHelper;
import com.example.app.db.GroupDBHelper;
import com.example.app.model.Chengjiao;
import com.example.app.model.Group;
import com.example.app.service.WebDataService;
import com.example.app.utils.UiUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created by hello on 2018/3/5.
 */

public class GroupActivity extends BackActivity {
    public static String GROUPID = "GroupId";
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
    @BindView(R.id.weituo)
    TextView weituoText;
    @BindView(R.id.ljjz)
    TextView ljjzText;
    @BindView(R.id.group_listview)
    ListView groupList;
    private GroupDBHelper groupDBHelper;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_group;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        groupId = getIntent().getStringExtra(GROUPID);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupActivity.this, SerachAddActivity.class);
                intent.putExtra(GroupActivity.GROUPID, groupId);
                GroupActivity.this.startActivity(intent);
            }
        });
        groupDBHelper = new GroupDBHelper(DBHelper.getRealm());

        updateData();

    }

    private void updateData() {
        Group group = groupDBHelper.findGroupByID(groupId);
        if (group != null) {
            totalText.setText(UiUtils.format2wei(group.getTotalValue()));
            dayprofitsText.setText(UiUtils.format2wei(group.getProfit() * 100));
            marketValueText.setText(UiUtils.format2wei(group.getMarketValue()));
            cashText.setText(UiUtils.format2wei(group.getCash()));
            weituoText.setText(group.getWeituo() + "");
            ljjzText.setText(UiUtils.format2wei(group.getLjjz()));
        }
        groupList.setAdapter(new GroupListAdapter(this, group));
    }

    private void setData() {

    }

    @Override
    protected void onResume() {
        updateData();
        super.onResume();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(false);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.groupmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_weituo:
                Intent intent1 = new Intent(this, WeituoActivity.class);
                intent1.putExtra(GroupActivity.GROUPID, groupId);
                startActivity(intent1);
                break;
            case R.id.action_chengjiao:
                Intent intent2 = new Intent(this, ChengjiaoActivity.class);
                intent2.putExtra(GroupActivity.GROUPID, groupId);
                startActivity(intent2);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateZhibiao(WebDataService.UpdateJingzhiEvent event) {
        updateData();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
