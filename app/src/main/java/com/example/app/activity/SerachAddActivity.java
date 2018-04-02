package com.example.app.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.db.DBHelper;
import com.example.app.db.JingZhiDBHelper;
import com.example.app.model.Jingzhi;
import com.example.app.utils.UiUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SerachAddActivity extends BackActivity implements SearchView.OnQueryTextListener {
    private SearchAdapter adapter;
    private ListView listView;
    private JingZhiDBHelper jingZhiDBHelper;
    private TextView textView_result;
    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        jingZhiDBHelper =new JingZhiDBHelper(DBHelper.getRealm());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search_add;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout searchLayout = (LinearLayout) inflater.inflate(R.layout.search_input_text, null);
        adapter = new SearchAdapter(this, R.id.listView);
        listView = (ListView) this.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
        textView_result = (TextView) findViewById(R.id.text_search);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        SearchView searchView = (SearchView) searchLayout.findViewById(R.id.editText);
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        EditText textView = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        Drawable img = ContextCompat.getDrawable(this, R.mipmap.icon_search);
        img.setBounds(0, 0, 60, 60);

        textView.setCompoundDrawablePadding(5);
        textView.setCompoundDrawables(img, null, null, null);
        textView.setHint("搜索");
        actionBar.setCustomView(searchLayout);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String value) {
      return true;
    }

    @Override
    public boolean onQueryTextChange(String value) {

        return false;
    }


}
