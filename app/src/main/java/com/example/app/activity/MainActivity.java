package com.example.app.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.adapter.MainListAdapter;
import com.example.app.db.DBHelper;
import com.example.app.db.GroupDBHelper;
import com.example.app.mail.SendMailUtil;
import com.example.app.model.ChiCang;
import com.example.app.model.Group;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.RealmList;

/**
 * Created by hello on 2018/3/5.
 */

public class MainActivity extends BackActivity {

    @BindView(R.id.main_listview)
    ListView listView;

    private List<Group> datas = new ArrayList<>();
    private MainListAdapter mainListAdapter;
    private GroupDBHelper groupDBHelper;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

        groupDBHelper = new GroupDBHelper(DBHelper.getRealm());
        mainListAdapter = new MainListAdapter(this, datas);
        listView.setAdapter(mainListAdapter);
        updateList();
    }

    private void updateList() {
        datas = groupDBHelper.findAllGroup();
        mainListAdapter.setData(datas);
        mainListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(false);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @OnClick(R.id.daochu)
    public void daochu() {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.txt");
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            String str = "hello world";
            byte[] data = str.getBytes();
            os.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            try {
                if (os != null) os.close();
            } catch (IOException e) {
            }
        }
        SendMailUtil.send(file, editText.getText().toString());
    }

    @OnClick(R.id.daochuname)
    public void setDaoChuName() {
        Toast.makeText(this, "daochuname", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.update)
    public void update() {
        Toast.makeText(this, "update", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.updateDate)
    public void updateDate() {
        Toast.makeText(this, "updatedate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_jia) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.main_dialog, null);
            final EditText zuheName = view.findViewById(R.id.dialog_zuhe_name);
            final EditText zuheTotal = view.findViewById(R.id.dialog_zuhe_total);
            builder.setView(view);
            builder.setTitle("创建组合");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    if (zuheName.getText() == null || zuheTotal.getText() == null) {
                        try {
                            Field field = dialog.getClass().getSuperclass()
                                    .getDeclaredField("main_dialog"); // 这个是reflect的那个Field
                            field.setAccessible(true);
                            field.set(dialog, false); // false -/ 使之不能关闭(此为机关所在，其它语句相同)
                        } catch (Exception e) {
                            Log.e(e.getMessage(), null);
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, "组合名字和初始资金不可为空", Toast.LENGTH_SHORT).show();
                    } else {
                        Group group = new Group();
                        group.setName(zuheName.getText().toString());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
                        group.setStartDate(formatter.format(new Date(System.currentTimeMillis())).toString());
                        group.setId(String.valueOf(System.currentTimeMillis()));
                        Double total = Double.parseDouble(zuheTotal.getText().toString());
                        group.setStartValue(total);
                        group.setTotalValue(total);
                        group.setCash(total);
                        group.setMarketValue(0.0);
                        group.setMostLost(0.0);
                        group.setLjjz(1.0);
                        group.setChicang(new RealmList<ChiCang>());
                        group.setStatus(0);
                        group.setProfit(0.0);
                        DBHelper dbHelper = new DBHelper(DBHelper.getRealm());
                        dbHelper.saveOrUpdate(group);
                        updateList();
                    }

                }
            });

            builder.setCancelable(false);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
