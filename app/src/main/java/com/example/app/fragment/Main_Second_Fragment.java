package com.example.app.fragment;

import android.view.View;
import android.widget.GridView;

import com.example.app.R;
import com.example.app.adapter.MainSixPartTableAdapter;
import com.example.app.adapter.SecondIndicatorsAdapter;
import com.example.app.db.DBHelper;
import com.example.app.db.MainMapDBHelper;
import com.example.app.db.MainSixpartDBHelper;
import com.example.app.dto.MainSixPartDto;
import com.example.app.dto.SecondIndicatorsModel;
import com.example.app.model.MainMapModel;
import com.example.app.service.WebDataService;
import com.example.app.utils.UiUtils;
import com.example.app.view.LinearLayoutForListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hello on 2018/3/5.
 */

public class Main_Second_Fragment extends BaseFragment {
    @BindView(R.id.main_sixpart_tablelayout)
    GridView gridView;
    @BindView(R.id.indicators)
    LinearLayoutForListView listView;

    @Override
    protected void bindUI(View view) {

        getMainSixPartDatas();
        getListData();
        EventBus.getDefault().register(this);
    }

    private void getMainSixPartDatas() {
        List<MainSixPartDto> datas = new ArrayList<>();
        MainSixpartDBHelper dbHelper = new MainSixpartDBHelper(DBHelper.getRealm());
        MainSixPartDto mainSixPartDto0 = new MainSixPartDto(dbHelper.findWDHY(0));
        datas.add(mainSixPartDto0);
        if (dbHelper.findallSWLY().size() == 2) {
            MainSixPartDto mainSixPartDto1 = new MainSixPartDto(dbHelper.findallSWLY().get(0), dbHelper.findallSWLY().get(1));
            datas.add(mainSixPartDto1);
        }
        MainSixPartDto mainSixPartDto2 = new MainSixPartDto(dbHelper.findCBD(0));
        datas.add(mainSixPartDto2);
        MainSixPartDto mainSixPartDto3 = new MainSixPartDto(dbHelper.findJYZS(0));
        datas.add(mainSixPartDto3);
        if (dbHelper.findallGDZC().size() == 2) {
            MainSixPartDto mainSixPartDto4 = new MainSixPartDto(dbHelper.findallGDZC().get(0), dbHelper.findallGDZC().get(1));
            datas.add(mainSixPartDto4);
        }
        if (dbHelper.findallArea().size() == 2) {
            MainSixPartDto mainSixPartDto4 = new MainSixPartDto(dbHelper.findallArea().get(0), dbHelper.findallArea().get(1));
            datas.add(mainSixPartDto4);
        }
        MainSixPartTableAdapter mainSixPartTableAdapter = new MainSixPartTableAdapter(getContext(), datas);
        gridView.setAdapter(mainSixPartTableAdapter);
    }

    private void getListData() {
        List<SecondIndicatorsModel> datas1 = new ArrayList<>();
        MainMapDBHelper dbHelper = new MainMapDBHelper(DBHelper.getRealm());
        MainMapModel modelquji1 = dbHelper.findCompaniesListView("s-8478-2");
        MainMapModel modelquji2 = dbHelper.findCompaniesListView("s-8478-669");
        MainMapModel modelquji3 = dbHelper.findCompaniesListView("s-8478-11");
        if (modelquji1 != null && modelquji2 != null && modelquji3 != null) {
            String s;
            if (modelquji1.getStatMonth().equals("1")) {
                s = "1";
            } else {
                s = "1-" + modelquji1.getStatMonth();
            }
            datas1.add(new SecondIndicatorsModel("类目", s + "月累计", "增长(%)", "进度评价"));
            datas1.add(new SecondIndicatorsModel("区级一般公共预算收入", UiUtils.formatYI(modelquji1.getDataValue()), UiUtils.format1wei(modelquji2.getDataValue()), formatPingjia(modelquji3.getDataValue(), modelquji3.getStatMonth())));
        }
        MainMapModel modellingshou1 = dbHelper.findCompaniesListView("s-8633-2");
        MainMapModel modellingshou2 = dbHelper.findCompaniesListView("s-8633-669");
        MainMapModel modellingshou3 = dbHelper.findCompaniesListView("s-8633-11");
        if (modellingshou1 != null && modellingshou3 != null && modellingshou2 != null) {
            datas1.add(new SecondIndicatorsModel("社会消费品零售总额", UiUtils.formatYI(modellingshou1.getDataValue()), UiUtils.format1wei(modellingshou2.getDataValue()), formatPingjia(modellingshou3.getDataValue(), modellingshou3.getStatMonth())));
        }
        MainMapModel modelguding1 = dbHelper.findCompaniesListView("s-165-2");
        MainMapModel modelguding2 = dbHelper.findCompaniesListView("s-165-669");
        MainMapModel modelguding3 = dbHelper.findCompaniesListView("s-165-11");
        if (modelguding1 != null && modelguding3 != null && modelguding2 != null) {
            datas1.add(new SecondIndicatorsModel("全社会固定资产投资额", UiUtils.formatYI(modelguding1.getDataValue()), UiUtils.format1wei(modelguding2.getDataValue()), formatPingjia(modelguding3.getDataValue(), modelguding3.getStatMonth())));
        }
        MainMapModel modelDongqian1 = dbHelper.findCompaniesListView("s-8871-2");
        MainMapModel modelDongqian2 = dbHelper.findCompaniesListView("s-8871-669");
        MainMapModel modelDongqian3 = dbHelper.findCompaniesListView("s-8871-11");
        if (modelDongqian1 != null && modelDongqian3 != null && modelDongqian2 != null) {
            datas1.add(new SecondIndicatorsModel("动迁及征收居民户数(户)", modelDongqian1.getDataValue(), UiUtils.format1wei(modelDongqian2.getDataValue()), formatPingjia(modelDongqian3.getDataValue(), modelDongqian3.getStatMonth())));
        }
        MainMapModel modelXinzeng1 = dbHelper.findCompaniesListView("s-623-2");
        MainMapModel modelXinzeng2 = dbHelper.findCompaniesListView("s-623-669");
        MainMapModel modelXinzeng3 = dbHelper.findCompaniesListView("s-623-11");
        if (modelXinzeng1 != null && modelXinzeng3 != null && modelXinzeng2 != null) {
            datas1.add(new SecondIndicatorsModel("新增就业岗位数(个)", modelXinzeng1.getDataValue(), UiUtils.format1wei(modelXinzeng2.getDataValue()), formatPingjia(modelXinzeng3.getDataValue(), modelXinzeng3.getStatMonth())));
        }
        SecondIndicatorsAdapter secondIndicatorsAdapter = new SecondIndicatorsAdapter(getContext(), datas1);
        listView.bindLinearLayout(secondIndicatorsAdapter);

    }

    private String formatPingjia(String wcnjh, String month) {
        String result;
        if (Float.parseFloat(wcnjh) >= 100) {
            result = "完成全年指标任务";
        } else if (Float.parseFloat(wcnjh) / Float.parseFloat(month) > 100 / 12) {
            result = "超过日历进度";
        } else {
            result = "落后日历进度";
        }
        return result;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_second;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateSixPart(WebDataService.UpdateSixPartEvent event) {
        getMainSixPartDatas();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateZhibiao(WebDataService.UpdateZhibiaoEvent event) {
        getListData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
