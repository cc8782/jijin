package com.example.app.dto;

import com.example.app.model.MainSixPartAreaModel;
import com.example.app.model.MainSixPartCBDModel;
import com.example.app.model.MainSixPartGDZCModel;
import com.example.app.model.MainSixPartJYZSModel;
import com.example.app.model.MainSixPartSWLYModel;
import com.example.app.model.MainSixPartWDHYModel;
import com.example.app.utils.UiUtils;

/**
 * Created by hello on 2018/3/6.
 */

public class MainSixPartDto {
    String part;
    String title;

    String value;
    String basis;

    public MainSixPartDto(String part, String title, String value) {
        this.part = part;
        this.title = title;
        this.value = value;
    }

    public MainSixPartDto(MainSixPartWDHYModel mainSixPartWDHYModel) {
        this.part = "产业经济";
        if (null != mainSixPartWDHYModel) {
            if (null != mainSixPartWDHYModel.getYearmonth()) {
                String month = mainSixPartWDHYModel.getYearmonth().split("-")[1];
                this.title = onToMonth(month) + "月五大产业税收合计";
            }
            if (null != mainSixPartWDHYModel.getWdhyTax()) {
                this.value = mainSixPartWDHYModel.getWdhyTax() + "亿元";
            }
            if (null != mainSixPartWDHYModel.getWdhyTaxZzl()) {
                Float f = Float.parseFloat(mainSixPartWDHYModel.getWdhyTaxZzl());
                this.basis = String.format("%.2f", f) + "%";
            }
        }

    }


    public MainSixPartDto(MainSixPartSWLYModel model1,MainSixPartSWLYModel model2) {

        this.part = "商务楼宇";

            MainSixPartSWLYModel mainSixPartSWLYModel = new MainSixPartSWLYModel();
            if (null != model1.getMONTH()) {
                this.setTitle(onToMonth(model1.getMONTH()) + "月楼宇税收合计");
            }
            if (null != model1.getVALUE()) {
                this.value = model1.getVALUE() + "亿元";
            }
            if (null != model2.getVALUE()) {
                this.basis = model2.getVALUE() + "%";
            }


    }
    public MainSixPartDto(MainSixPartGDZCModel model1,MainSixPartGDZCModel model2) {
        this.part = "城市建设" ;

        if (null != model1.getMONTH()) {
            this.setTitle(onToMonth(model2.getMONTH()) + "月固定资产投资总额");
        }
        if (null != model1.getVALUE()) {
            this.value = model1.getVALUE() + "亿元";
        }
        if (null != model2.getVALUE()) {
            Float f = Float.parseFloat(model2 .getVALUE());
            this.basis = String.format("%.2f", f) + "%";
        }


    }

    public MainSixPartDto(MainSixPartAreaModel model1, MainSixPartAreaModel model2) {
        this.part = "城市建设" ;

        if (null != model1.getStatMonth()) {
            this.setTitle(onToMonth(model1.getStatMonth()) + "月一般公共预算支出总额");
        }

        if (null != model1.getDataValue()) {

            this.value = UiUtils.formatYI(model1.getDataValue()) + "亿元";

        }

        if (null != model2.getDataValue()) {
            this.basis =model2 .getDataValue() + "%";
        }


    }
    public MainSixPartDto(MainSixPartCBDModel model) {
        this.part = "CBD/商圈";
        if (null != model) {
            if (null != model.getMonth()) {
                this.title = onToMonth(model.getMonth()) + "月主要商圈累计刷卡额";
            }
            if (null != model.getAmount()) {
                this.value=   UiUtils.formatYI(model.getAmount())+"亿元";

            }

        }

    }

    public MainSixPartDto(MainSixPartJYZSModel model) {
        this.part = "企业运营";
        if (null != model) {
            if (null != model.getMONTH()) {
                String month = model.getMONTH().substring(4, 6);
                this.title = onToMonth(month) + "月Top1000指数";
            }
            if (null != model.getJYZS()) {
                this.value = model.getJYZS();
            }
        }

    }


    public MainSixPartDto(String part, String title, String value, String basis) {
        this.part = part;
        this.title = title;
        this.value = value;
        this.basis = basis;
    }

    public String getPart() {
        return part;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String onToMonth(String month) {
        if (Integer.valueOf(month) != 1) {
            return "1-" + Integer.valueOf(month);
        } else {
            return "1";
        }
    }
}
