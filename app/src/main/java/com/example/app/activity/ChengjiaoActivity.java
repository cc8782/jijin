package com.example.app.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.adapter.ChengjiaoAdapter;
import com.example.app.adapter.WeituoAdapter;
import com.example.app.db.ChengjiaoDBHelper;
import com.example.app.db.DBHelper;
import com.example.app.db.WeituoDBHelper;
import com.example.app.model.Chengjiao;
import com.example.app.model.Weituo;

import java.util.ArrayList;
import java.util.List;

public class ChengjiaoActivity extends BackActivity  {

    private ListView listView;
private ChengjiaoDBHelper chengjiaoDBHelper;
    private TextView textView_result;
    private List<Chengjiao> chengjiaos=new ArrayList<>();
    private ChengjiaoAdapter chengjiaoAdapter;
    private String groupID;
    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        chengjiaoDBHelper =new ChengjiaoDBHelper(DBHelper.getRealm());
        groupID=getIntent().getStringExtra(GroupActivity.GROUPID);
        chengjiaos=chengjiaoDBHelper.findbyGroupId(groupID);
        chengjiaoAdapter =new ChengjiaoAdapter(this,chengjiaos,groupID);
        listView = (ListView) this.findViewById(R.id.weittuo);
        listView.setAdapter(chengjiaoAdapter);
        updatepage();
    }
    private void updatepage(){
        chengjiaoAdapter.setData(chengjiaos);
        chengjiaoAdapter.notifyDataSetChanged();
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_chengjiao;
    }








}
