package com.example.app.activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.app.R;
import com.example.app.adapter.ThirdCenterAdapter;
import com.example.app.cache.Global;
import com.example.app.dto.ThirdCenterGdpModel;
import com.example.app.view.LinearLayoutForListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hello on 2018/3/5.
 */

public class RenkouActivity extends BackActivity {
@BindView(R.id.renkou_list)
    LinearLayoutForListView listViewRenkou;
    @BindView(R.id.web_population)
    WebView webView;
    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        getRenkouData();

        webView.getSettings().setSupportZoom(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient(){});
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Global.BASE_WEB_URL+Global.WEB_POPULATION);
    }

    private void getRenkouData() {
  String str="[\n" +
          "            {pinyin: 'ppxcjd', name: '彭浦新村街道', hjrks: 121099, syrks: 143285, lhrks: 25108, jdmj: 3.81, rkmd: 37608,jwhsl:33},\n" +
          "            {pinyin: 'ppzjd', name: '彭浦镇', hjrks: 98260, syrks: 144197, lhrks: 34052, jdmj: 7.89, rkmd: 18276,jwhsl:40},\n" +
          "            {pinyin: 'lfljd', name: '临汾路街道', hjrks: 58164, syrks: 72775, lhrks: 11560, jdmj: 1.99, rkmd: 36570,jwhsl:20},\n" +
          "            {pinyin: 'dnljd', name: '大宁路街道', hjrks: 62810, syrks: 92132, lhrks: 26379, jdmj: 5.87, rkmd: 15695,jwhsl:20},\n" +
          "            {pinyin: 'ghxljd', name: '共和新路街道', hjrks: 72275, syrks: 93990, lhrks: 22931, jdmj: 2.74, rkmd: 34303,jwhsl:24},\n" +
          "            {pinyin: 'zjxljd', name: '芷江西路街道', hjrks: 70398, syrks: 77384, lhrks: 22363, jdmj: 1.56, rkmd: 49605,jwhsl:18},\n" +
          "            {pinyin: 'tmxljd', name: '天目西路街道', hjrks: 33400, syrks: 33745, lhrks: 11147, jdmj: 1.95, rkmd: 17305,jwhsl:12},\n" +
          "            {pinyin: 'bsljd', name: '宝山路街道', hjrks: 80769, syrks: 76424, lhrks: 23053, jdmj: 1.61, rkmd: 47468,jwhsl:19},\n" +
          "            {pinyin: 'bjjd', name: '北站街道', hjrks: 68266, syrks: 46544, lhrks: 14368, jdmj: 1.78, rkmd: 26148,jwhsl:19},\n" +
          "            {pinyin: 'jnljd', name: '江宁路街道', hjrks: 75247, syrks: 73360, lhrks: 14339, jdmj: 1.84, rkmd: 39870,jwhsl:18},\n" +
          "            {pinyin: 'cjdjd', name: '曹家渡街道', hjrks: 72514, syrks: 82884, lhrks: 16825, jdmj: 1.49, rkmd: 55627,jwhsl:14},\n" +
          "            {pinyin: 'jasjd', name: '静安寺街道', hjrks: 38324, syrks: 37316, lhrks: 6639, jdmj: 1.57, rkmd: 23768,jwhsl:11},\n" +
          "            {pinyin: 'njxljd', name: '南京西路街道', hjrks: 51216, syrks: 42182, lhrks: 8759, jdmj: 1.62, rkmd: 26038,jwhsl:13},\n" +
          "            {pinyin: 'smeljd', name: '石门二路街道', hjrks: 39284, syrks: 32523, lhrks: 5621, jdmj: 1.07, rkmd: 30395,jwhsl:13}\n" +
          "        ]";

        try {
            JSONArray array=new JSONArray(str);
            List<ThirdCenterGdpModel> data=new ArrayList<>();
            data.add(new ThirdCenterGdpModel("区域","户籍人口","实际人口","来沪人口","人口密度"));
            if(array.length()>0){
                for(int i=0;i<array.length();i++){
                    JSONObject object=array.getJSONObject(i);
                    ThirdCenterGdpModel thirdCenterGdpModel=new ThirdCenterGdpModel();
                    thirdCenterGdpModel.setLeimu(object.getString("name"));
                    thirdCenterGdpModel.setLeiji(object.getString("hjrks")+"人");
                    thirdCenterGdpModel.setZengzhang(object.getString("syrks")+"人");
                    thirdCenterGdpModel.setBizhong(object.getString("lhrks")+"人");
                    thirdCenterGdpModel.setPaiming(object.getString("rkmd")+"人/km²");
                    data.add(thirdCenterGdpModel);
                }
            }
            ThirdCenterAdapter adapter=new ThirdCenterAdapter(this,data);
            listViewRenkou.bindLinearLayout(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_renkou;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
