package com.example.app.fragment;

import android.view.View;
import android.widget.ScrollView;

import com.example.app.R;
import com.example.app.adapter.ThirdBudgetAdapter;
import com.example.app.adapter.ThirdCenterAdapter;
import com.example.app.db.DBHelper;
import com.example.app.db.MainMapDBHelper;
import com.example.app.dto.ThirdBudgetModel;
import com.example.app.dto.ThirdCenterGdpModel;
import com.example.app.model.MainMapModel;
import com.example.app.service.WebDataService;
import com.example.app.utils.UiUtils;
import com.example.app.view.LinearLayoutForListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hello on 2018/3/5.
 */

public class Main_Third_Fragment extends BaseFragment {
    @BindView(R.id.budget)
    LinearLayoutForListView budgetView;
    @BindView(R.id.third_scroll)
    ScrollView scrollView;
    @BindView(R.id.center_gdpp)
    LinearLayoutForListView centerGdpView;
    private ThirdBudgetAdapter thirdBudgetAdapter;

    @Override
    protected void bindUI(View view) {
        getBugdetData();
getCenterData();
EventBus.getDefault().register(this);

     // UiUtils.setListViewHeightBasedOnChildren(listView);
       // UiUtils.setListViewHeightBasedOnChildren(listViewCenter);
    }
    private void getBugdetData(){
        List<ThirdBudgetModel> datas1=new ArrayList<>();
        MainMapDBHelper dbHelper=new MainMapDBHelper(DBHelper.getRealm());
        MainMapModel huangpu1 = dbHelper.findCompaniesListView("s-8489-2");
        MainMapModel huangpu2= dbHelper.findCompaniesListView("s-8489-669");
        MainMapModel xuhui1 = dbHelper.findCompaniesListView("s-8488-2");
        MainMapModel xuhui2= dbHelper.findCompaniesListView("s-8488-669");
        MainMapModel changning1 = dbHelper.findCompaniesListView("s-8487-2");
        MainMapModel changning2= dbHelper.findCompaniesListView("s-8487-669");
        MainMapModel putuo1 = dbHelper.findCompaniesListView("s-8486-2");
        MainMapModel putuo2= dbHelper.findCompaniesListView("s-8486-669");
        MainMapModel jingan1 = dbHelper.findCompaniesListView("s-8485-2");
        MainMapModel jingan2= dbHelper.findCompaniesListView("s-8485-669");
        MainMapModel hongkou1 = dbHelper.findCompaniesListView("s-8484-2");
        MainMapModel hongkou2= dbHelper.findCompaniesListView("s-8484-669");
        MainMapModel yangpu1 = dbHelper.findCompaniesListView("s-8483-2");
        MainMapModel yangpu2= dbHelper.findCompaniesListView("s-8483-669");
        if(huangpu1==null||huangpu2==null||xuhui1==null||xuhui2==null||changning1==null||changning2==null
                ||putuo1==null||putuo2==null||jingan1==null||jingan2==null||hongkou1==null||hongkou2==null||yangpu1==null||yangpu2==null){
            return;
        }
        String[] paiming={huangpu1.getDataValue(),xuhui1.getDataValue(),changning1.getDataValue(),putuo1.getDataValue(),jingan1.getDataValue(),hongkou1.getDataValue(),yangpu1.getDataValue()};
        List<String> pm=Arrays.asList(paiming);
        Collections.sort(pm, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

            String s;
            if( huangpu1.getStatMonth().equals("1")){
                s="1";
            }else {
                s="1-"+huangpu1.getStatMonth();
            }
            datas1.add(new ThirdBudgetModel("类目",s+"月累计","增长(%)","排名"));
            datas1.add(new ThirdBudgetModel("黄浦区",UiUtils.formatYI(huangpu1.getDataValue()),UiUtils.format1wei(huangpu2.getDataValue()),String.valueOf(pm.indexOf(huangpu1.getDataValue())+1)));
            datas1.add(new ThirdBudgetModel("徐汇区",UiUtils.formatYI(xuhui1.getDataValue()),UiUtils.format1wei(xuhui2.getDataValue()),String.valueOf(pm.indexOf(xuhui1.getDataValue())+1)));
            datas1.add(new ThirdBudgetModel("长宁区",UiUtils.formatYI(changning1.getDataValue()),UiUtils.format1wei(changning2.getDataValue()),String.valueOf(pm.indexOf(changning1.getDataValue())+1)));
            datas1.add(new ThirdBudgetModel("普陀区",UiUtils.formatYI(putuo1.getDataValue()),UiUtils.format1wei(putuo2.getDataValue()),String.valueOf(pm.indexOf(putuo1.getDataValue())+1)));
            datas1.add(new ThirdBudgetModel("静安区",UiUtils.formatYI(jingan1.getDataValue()),UiUtils.format1wei(jingan2.getDataValue()),String.valueOf(pm.indexOf(jingan1.getDataValue())+1)));
            datas1.add(new ThirdBudgetModel("虹口区",UiUtils.formatYI(hongkou1.getDataValue()),UiUtils.format1wei(hongkou2.getDataValue()),String.valueOf(pm.indexOf(hongkou1.getDataValue())+1)));
            datas1.add(new ThirdBudgetModel("杨浦区",UiUtils.formatYI(yangpu1.getDataValue()),UiUtils.format1wei(yangpu2.getDataValue()),String.valueOf(pm.indexOf(yangpu1.getDataValue())+1)));
        thirdBudgetAdapter=new ThirdBudgetAdapter(getContext(),datas1);
        budgetView.bindLinearLayout(thirdBudgetAdapter);

    }
    private void getCenterData(){
        List<ThirdCenterGdpModel> datas1=new ArrayList<>();
        MainMapDBHelper dbHelper=new MainMapDBHelper(DBHelper.getRealm());
        MainMapModel huangpu1 = dbHelper.findCompaniesListView("s-4411-16");
        MainMapModel huangpu2= dbHelper.findCompaniesListView("s-4411-10");
        MainMapModel huangpu3= dbHelper.findCompaniesListView("s-4411-17");
        MainMapModel xuhui1 = dbHelper.findCompaniesListView("s-4412-16");
        MainMapModel xuhui2= dbHelper.findCompaniesListView("s-4412-10");
        MainMapModel xuhui3= dbHelper.findCompaniesListView("s-4412-17");
        MainMapModel changning1 = dbHelper.findCompaniesListView("s-4413-16");
        MainMapModel changning2= dbHelper.findCompaniesListView("s-4413-10");
        MainMapModel changning3= dbHelper.findCompaniesListView("s-4413-17");
        MainMapModel putuo1 = dbHelper.findCompaniesListView("s-4414-16");
        MainMapModel putuo2= dbHelper.findCompaniesListView("s-4414-10");
        MainMapModel putuo3= dbHelper.findCompaniesListView("s-4414-17");
        MainMapModel jingan1 = dbHelper.findCompaniesListView("s-4530-16");
        MainMapModel jingan2= dbHelper.findCompaniesListView("s-4530-10");
        MainMapModel jingan3= dbHelper.findCompaniesListView("s-4530-17");
        MainMapModel hongkou1 = dbHelper.findCompaniesListView("s-4416-16");
        MainMapModel hongkou2= dbHelper.findCompaniesListView("s-4416-10");
        MainMapModel hongkou3= dbHelper.findCompaniesListView("s-4416-17");
        MainMapModel yangpu1 = dbHelper.findCompaniesListView("s-4417-16");
        MainMapModel yangpu2= dbHelper.findCompaniesListView("s-4417-10");
        MainMapModel yangpu3= dbHelper.findCompaniesListView("s-4417-17");
        if(huangpu1==null||huangpu2==null||xuhui1==null||xuhui2==null||changning1==null||changning2==null
                ||putuo1==null||putuo2==null||jingan1==null||jingan2==null||hongkou1==null||hongkou2==null||yangpu1==null||yangpu2==null){
            return;
        }
        String[] paiming={huangpu3.getDataValue(),xuhui3.getDataValue(),changning3.getDataValue(),putuo3.getDataValue(),jingan3.getDataValue(),hongkou3.getDataValue(),yangpu3.getDataValue()};
        List<String> pm=Arrays.asList(paiming);
        Collections.sort(pm, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        String str="";
        int quar =(Integer.parseInt(huangpu1.getStatMonth())/ 3);
        if(quar == 1){
            str = huangpu1.getStatYear()+"年一季度止";
        }else if(quar == 2){
            str = huangpu1.getStatYear()+"年上半年度";
        }else if(quar == 3){
            str = huangpu1.getStatYear()+"年三季度止";
        }else if(quar == 4){
            str = huangpu1.getStatYear()+"年全年";
        }
        datas1.add(new ThirdCenterGdpModel("类目",str,"增长(%)","第三产业比重(%)","排名"));
        datas1.add(new ThirdCenterGdpModel("黄浦区",UiUtils.formatYI(huangpu1.getDataValue()),UiUtils.format1wei(huangpu2.getDataValue()),UiUtils.format1wei(huangpu3.getDataValue()),String.valueOf(pm.indexOf(huangpu3.getDataValue())+1)));
        datas1.add(new ThirdCenterGdpModel("徐汇区",UiUtils.formatYI(xuhui1.getDataValue()),UiUtils.format1wei(xuhui2.getDataValue()),UiUtils.format1wei(xuhui3.getDataValue()),String.valueOf(pm.indexOf(xuhui3.getDataValue())+1)));
        datas1.add(new ThirdCenterGdpModel("长宁区",UiUtils.formatYI(changning1.getDataValue()),UiUtils.format1wei(changning2.getDataValue()),UiUtils.format1wei(changning3.getDataValue()),String.valueOf(pm.indexOf(changning3.getDataValue())+1)));
        datas1.add(new ThirdCenterGdpModel("静安区",UiUtils.formatYI(jingan1.getDataValue()),UiUtils.format1wei(jingan2.getDataValue()),UiUtils.format1wei(jingan3.getDataValue()),String.valueOf(pm.indexOf(jingan3.getDataValue())+1)));
        datas1.add(new ThirdCenterGdpModel("普陀区",UiUtils.formatYI(putuo1.getDataValue()),UiUtils.format1wei(putuo2.getDataValue()),UiUtils.format1wei(putuo3.getDataValue()),String.valueOf(pm.indexOf(putuo3.getDataValue())+1)));

        datas1.add(new ThirdCenterGdpModel("虹口区",UiUtils.formatYI(hongkou1.getDataValue()),UiUtils.format1wei(hongkou2.getDataValue()),UiUtils.format1wei(hongkou3.getDataValue()),String.valueOf(pm.indexOf(hongkou3.getDataValue())+1)));
        datas1.add(new ThirdCenterGdpModel("杨浦区",UiUtils.formatYI(yangpu1.getDataValue()),UiUtils.format1wei(yangpu2.getDataValue()),UiUtils.format1wei(yangpu3.getDataValue()),String.valueOf(pm.indexOf(yangpu3.getDataValue())+1)));
        ThirdCenterAdapter thirdCenterAdapter=new ThirdCenterAdapter(getContext(),datas1);
        centerGdpView.bindLinearLayout(thirdCenterAdapter);

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_third;
    }
    @Subscribe(threadMode = ThreadMode.MAIN , sticky = true)
    public void update(WebDataService.UpdateBudgetEvent event){
          getBugdetData();
    }
    @Subscribe(threadMode = ThreadMode.MAIN , sticky = true)
    public void update(WebDataService.UpdateCenterEvent event){
            getCenterData();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
