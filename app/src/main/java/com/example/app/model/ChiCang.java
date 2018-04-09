package com.example.app.model;

import io.realm.RealmObject;

/**
 * Created by hello on 2018/3/8.
 */

public class ChiCang extends RealmObject {
  private Double chicangliang;
  private Jingzhi jingzhi;

  public ChiCang() {
  }

  public Double getChicangliang() {
    return chicangliang;
  }

  public void setChicangliang(Double chicangliang) {
    this.chicangliang = chicangliang;
  }

  public Jingzhi getJingzhi() {
    return jingzhi;
  }

  public void setJingzhi(Jingzhi jingzhi) {
    this.jingzhi = jingzhi;
  }
}
