package com.example.app.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.adapter.SearchJingzhiAdapter;
import com.example.app.adapter.WeituoAdapter;
import com.example.app.db.DBHelper;
import com.example.app.db.JingZhiDBHelper;
import com.example.app.db.WeituoDBHelper;
import com.example.app.model.Jingzhi;
import com.example.app.model.Weituo;

import java.util.ArrayList;
import java.util.List;

public class WeituoActivity extends BackActivity  {

    private ListView listView;
private WeituoDBHelper weituoDBHelper;
    private TextView textView_result;
    private List<Weituo> weituos=new ArrayList<>();
    private WeituoAdapter weituoAdapter;
    private String groupID;
    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        weituoDBHelper =new WeituoDBHelper(DBHelper.getRealm());
        groupID=getIntent().getStringExtra(GroupActivity.GROUPID);
        weituos=weituoDBHelper.findbyGroupId(groupID);
        weituoAdapter =new WeituoAdapter(this,weituos,groupID);
        listView = (ListView) this.findViewById(R.id.weittuo);
        listView.setAdapter(weituoAdapter);
        updatepage();
    }
    private void updatepage(){
        weituoAdapter.setData(weituos);
        weituoAdapter.notifyDataSetChanged();
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_weituo;
    }








}
