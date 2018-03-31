package com.example.app.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.db.DBHelper;
import com.example.app.db.MainMapDBHelper;
import com.example.app.model.MainMapCompanyNumbersModel;
import com.example.app.service.WebDataService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by hello on 2018/3/5.
 */

public class Main_First_Fragment extends BaseFragment {
    @BindView(R.id.main_all_ss)
    TextView mainAllSs;
    @BindView(R.id.main_all_part)
    TextView mainAllPart;
    @BindView(R.id.main_all_qy)
    TextView mainAllQy;
    @BindView(R.id.main_zh_ss)
    TextView mainZhSs;
    @BindView(R.id.main_zh_part)
    TextView mainZhPart;
    @BindView(R.id.main_zh_qy)
    TextView mainZhQy;
    @BindView(R.id.main_suriver_ss)
    TextView mainSuriverSs;
    @BindView(R.id.main_suriver_part)
    TextView mainSuriverPart;
    @BindView(R.id.main_suriver_qy)
    TextView mainSuriverQy;
    @BindView(R.id.main_nanroad_ss)
    TextView mainNanroadSs;
    @BindView(R.id.main_nanroad_part)
    TextView mainNanroadPart;
    @BindView(R.id.main_nanroad_qy)
    TextView mainNanroadQy;
    Unbinder unbinder;

    @Override
    protected void bindUI(View view) {
        initData();
        EventBus.getDefault().register(this);
    }

    private void initData() {
        MainMapDBHelper dbHelper=new MainMapDBHelper(DBHelper.getRealm());

        MainMapCompanyNumbersModel numbersModel=dbHelper.findCompanies(0);
        if(null!=numbersModel){
            if(null!=numbersModel.getTotal()){
                mainAllQy.setText(numbersModel.getTotal()+"户");
            }
            if(null!=numbersModel.getNanjingxilu()){
                mainNanroadQy.setText(numbersModel.getNanjingxilu()+"户");
            }
            if(null!=numbersModel.getSuzhouhe()){
                mainSuriverQy.setText(numbersModel.getSuzhouhe()+"户");
            }
            if(null!=numbersModel.getZhonghuan()){
                mainZhQy.setText(numbersModel.getZhonghuan()+"户");
            }
        }

        mainAllSs.setText(formatYi(dbHelper.findCompaniesListView("s-8638-2")==null?"0":dbHelper.findCompaniesListView("s-8638-2").getDataValue()));
        mainAllPart.setText(formatYi(dbHelper.findCompaniesListView("s-8639-2")==null?"0":dbHelper.findCompaniesListView("s-8638-2").getDataValue()));
        mainNanroadSs.setText(formatYi(dbHelper.findCompaniesListView("s-8635-2")==null?"0":dbHelper.findCompaniesListView("s-8638-2").getDataValue()));
        mainNanroadPart.setText(formatYi(dbHelper.findCompaniesListView("s-8640-2")==null?"0":dbHelper.findCompaniesListView("s-8638-2").getDataValue()));
        mainSuriverSs.setText(formatYi(dbHelper.findCompaniesListView("s-8636-2")==null?"0":dbHelper.findCompaniesListView("s-8638-2").getDataValue()));
        mainSuriverPart.setText(formatYi(dbHelper.findCompaniesListView("s-8641-2")==null?"0":dbHelper.findCompaniesListView("s-8638-2").getDataValue()));
        mainZhSs.setText(formatYi(dbHelper.findCompaniesListView("s-8637-2")==null?"0":dbHelper.findCompaniesListView("s-8638-2").getDataValue()));
        mainZhPart.setText(formatYi(dbHelper.findCompaniesListView("s-8642-2")==null?"0":dbHelper.findCompaniesListView("s-8638-2").getDataValue()));

    }
    private String formatYi(String num){
        float result= Float.parseFloat(num)/100000000f;
        DecimalFormat df = new DecimalFormat("0.00");
        return  df.format(result)+"亿元";
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_first;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN , sticky = true)
    public void update(WebDataService.UpdateMapEvent event){
            initData();
    }
}
