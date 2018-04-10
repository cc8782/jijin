package com.example.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.db.DBHelper;
import com.example.app.db.GroupDBHelper;
import com.example.app.db.JingZhiDBHelper;
import com.example.app.model.ChiCang;
import com.example.app.model.Group;
import com.example.app.model.Jingzhi;
import com.example.app.model.Weituo;
import com.example.app.utils.UiUtils;

import java.util.Date;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by hello on 2018/3/5.
 */

public class JiaoyiActivity extends BaseActivity {
    public static String JINGZHIDAIMA = "jignzhidaima";
    public static String INTENTSTATUS = "intentstatus";
    public Integer status;

    private String groupId;
    private String jingzhiDm;
    private Double tmp = 0.0;
    @BindView(R.id.jingzhi_name)
    TextView jingzhiName;
    @BindView(R.id.jingzhi_daima)
    TextView jingzhiDaima;
    @BindView(R.id.jingzhi_ljjz1)
    TextView jingzhiLjjz;
    @BindView(R.id.jignzhi_dwjz1)
    TextView jingzhiDwjz;
    @BindView(R.id.jingzhi_rzzz)
    TextView jingzhiRzzz;
    @BindView(R.id.jingzhi_rzzl)
    TextView jingzhiRzzl;

    @BindView(R.id.jiaoyi_cash)
    TextView jiaoyiCash;
    @BindView(R.id.buy_text)
    TextView buyText;
    @BindView(R.id.buy_edittext)
    EditText buyEditText;
    @BindView(R.id.buy_shui)
    EditText buyShui;

    @BindView(R.id.buy)
    RadioButton buy;
    @BindView(R.id.sell)
    RadioButton sell;


    @BindView(R.id.radioGroup)
    RadioGroup radiogroup;
    @BindView(R.id.cangwei)
    RadioGroup cangWei;
    @BindView(R.id.jiaoyi_queren)
    Button jiaoyiQueren;
    private Group group;
    private Jingzhi jingzhi;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_jiaoyi;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        Intent intent = getIntent();
        groupId = intent.getStringExtra(GroupActivity.GROUPID);
        jingzhiDm = intent.getStringExtra(JINGZHIDAIMA);


        JingZhiDBHelper jingZhiDBHelper = new JingZhiDBHelper(DBHelper.getRealm());
         jingzhi = jingZhiDBHelper.findJingzhiByID(jingzhiDm);

        if (UiUtils.bigThenZero(jingzhi.getRzzl())) {
            jingzhiRzzl.setTextColor(ContextCompat.getColor(this, R.color.red));
            jingzhiRzzz.setTextColor(ContextCompat.getColor(this, R.color.red));
        } else {
            jingzhiRzzl.setTextColor(ContextCompat.getColor(this, R.color.green));
            jingzhiRzzz.setTextColor(ContextCompat.getColor(this, R.color.green));
        }
        jingzhiName.setText(jingzhi.getName() + "");
        jingzhiDaima.setText(jingzhi.getDaima() + "");
        jingzhiDwjz.setText(UiUtils.formatdouble(jingzhi.getDwjz1()));
        jingzhiLjjz.setText(UiUtils.formatdouble(jingzhi.getLjjz1()));
        jingzhiRzzz.setText(UiUtils.formatdouble(jingzhi.getRzzz()));
        jingzhiRzzl.setText(UiUtils.formatdouble(jingzhi.getRzzl()));




        final GroupDBHelper groupDBHelper = new GroupDBHelper(DBHelper.getRealm());
        group = groupDBHelper.findGroupByID(groupId);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.buy:
                        buy();
                        break;
                    case R.id.sell:
                        sell();
                        break;
                }

            }
        });

        cangWei.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (tmp == 0) {
                    return;
                }
                switch (checkedId) {
                    case R.id.one_eight:
                        buyEditText.setText(Math.ceil(tmp / 8) + "");
                        break;
                    case R.id.one_four:
                        buyEditText.setText(Math.ceil(tmp / 4) + "");
                        break;
                    case R.id.one_two:
                        buyEditText.setText(Math.ceil(tmp / 2) + "");
                        break;
                    case R.id.one_one:
                        buyEditText.setText(Math.ceil(tmp) + "");
                        break;

                }
            }
        });

        jiaoyiQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buyEditText.getText().toString()==null || buyEditText.getText().toString().equals("")){
                    if(status == 0){
                        Toast.makeText(JiaoyiActivity.this, "委托买入金额不能为空", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(JiaoyiActivity.this, "委托卖出份额不能为空", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                if(tmp<Double.parseDouble(buyEditText.getText().toString())){
                    if(status == 0){
                        Toast.makeText(JiaoyiActivity.this, "委托买入金额不能大于组合现金", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(JiaoyiActivity.this, "委托卖出份额不能大于组合持有份额", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                Weituo weituo = new Weituo();
                weituo.setId(String.valueOf(System.currentTimeMillis()));
                weituo.setGroupId(groupId);
                weituo.setJingzhiDm(jingzhiDm);
                DBHelper dbHelper = new DBHelper(DBHelper.getRealm());
                if (status == 0) {
                    weituo.setBuyCash(Double.parseDouble( buyEditText.getText().toString()));
                    weituo.setBuyshuifei(Double.parseDouble(buyShui.getText().toString()));
                    weituo.setTransactionType(0);
                    weituo.setStatus(0);
                    weituo.setStartDate(new Date());
                    dbHelper.saveOrUpdate(weituo);
                    Realm realm=DBHelper.getRealm();
                    realm.beginTransaction();
                    group.setCash(group.getCash()-Double.parseDouble( buyEditText.getText().toString()));
                    if(null!=group.getWeituo()&&0.0!=group.getWeituo()){
                        group.setWeituo(Double.parseDouble( buyEditText.getText().toString())+group.getWeituo());
                    }else {
                        group.setWeituo(Double.parseDouble( buyEditText.getText().toString()));
                    }

                  realm.commitTransaction();
                    dbHelper.saveOrUpdate(group);
                    Toast.makeText(JiaoyiActivity.this, "委托买入" + jingzhi.getName() + buyEditText.getText().toString() + "元", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    weituo.setSellnumber(Double.parseDouble(buyEditText.getText().toString()));
                    weituo.setSellshuifei(Double.parseDouble(buyShui.getText().toString()));
                    weituo.setTransactionType(1);
                    weituo.setStatus(0);
                    weituo.setStartDate(new Date());
                    dbHelper.saveOrUpdate(weituo);
                    Toast.makeText(JiaoyiActivity.this, "委托卖出" + jingzhi.getName() + buyEditText.getText().toString() + "份", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void setData() {

    }

    private void buy() {
        buyEditText.setText("");
        cangWei.clearCheck();
        tmp = group.getCash();
        jiaoyiCash.setText("可用现金 " + group.getCash() + "元");
        buyText.setText("买入金额");
        jiaoyiQueren.setBackgroundColor(ContextCompat.getColor(JiaoyiActivity.this, R.color.red));
        jiaoyiQueren.setText("买入");
        buyShui.setText(UiUtils.formatShui(jingzhi.getSgfl()));
        status = 0;
    }

    private void sell() {
        cangWei.clearCheck();
        buyEditText.setText("");
        RealmList<ChiCang> chiCangs = group.getChicang();
        if (chiCangs.size() == 0) {
            jiaoyiCash.setText("无可售份额");
            tmp = 0.0;
        }
        for (ChiCang chiCang : chiCangs) {
            if (chiCang.getJingzhi().getDaima().equals(jingzhiDm)) {
                jiaoyiCash.setText("可售份额" + chiCang.getChicangliang() + "份");
                tmp = Double.valueOf(chiCang.getChicangliang());
            }
        }
        buyText.setText("卖出份额");
        jiaoyiQueren.setBackgroundColor(ContextCompat.getColor(JiaoyiActivity.this, R.color.green));
        jiaoyiQueren.setText("卖出");
        buyShui.setText(UiUtils.formatShui(jingzhi.getShfl()));
        status = 1;
    }

    @Override
    protected void onResume() {
        if (getIntent().getIntExtra(INTENTSTATUS, 0) == 0) {
            buy();
        } else {
            sell();
        }
        super.onResume();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
