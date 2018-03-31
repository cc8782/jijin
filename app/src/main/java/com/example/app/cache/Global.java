package com.example.app.cache;

/**
 * Created by Administrator on 2017/6/21.
 */

public class Global {

  //  public static String BASE_API_URL ="http://61.152.234.197:3498/dsp/front/";
    public static String BASE_API_URL ="http://10.0.34.169:8080/front/";
    public static String BASE_WEB_URL ="http://10.0.34.169:8080/phone/";

    public static String MAIN_SIXPART_WDHY_URL ="main/initWDHY.do";//五大产业税收合计
    public static String MAIN_SIXPART_SWLY_URL ="main/initSWLY.do";//商务楼宇税收合计
    public static String MAIN_SIXPART_CBD_URL ="main/initCBD.do";//静安区主要商圈累计刷卡额
    public static String MAIN_SIXPART_JYZS_URL ="main/initJYZS.do";// TOP1000企业指数
    public static String MAIN_SIXPART_GDZC_URL ="main/initGDZC.do";//固定资产投资总额
    public static String MAIN_SIXPART_AREA_URL ="main/initArea.do";//一般公共预算支出总额

  public static String MAIN_INIT_REPORT_URL ="main/initReport.do";//主要指标完成情况/一般公共预算完成情况/中心城区GDP完成情况（季报）
  public static String MAIN_MAP_COMPNAY_URL ="main/yzsdNSQY.do";//获取三带纳税企业数
//web
  public static String WEB_INDUSTRY ="industry/index.do?mid=1";//产业经济
  public static String WEB_BUSSINESS ="building/index.do?mid=2";//商务楼宇
  public static String WEB_CBD ="cbd/index.do?mid=3";//CBD
  public static String WEB_AREA ="area/index.do?mid=4";//CBD
  public static String WEB_POPULATION ="population/index.do";//人口社会



}
