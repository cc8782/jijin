package com.example.app.model;

import io.realm.RealmObject;

/**
 * Created by hello on 2018/3/8.
 */

public class ChiCang extends RealmObject {
  private Double chicangliang;

  private String daima;
  private String name;
  private String dwjz1;

  public ChiCang() {
  }

  public String getDaima() {
    return daima;
  }

  public void setDaima(String daima) {
    this.daima = daima;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDwjz1() {
    return dwjz1;
  }

  public void setDwjz1(String dwjz1) {
    this.dwjz1 = dwjz1;
  }

  public Double getChicangliang() {
    return chicangliang;
  }

  public void setChicangliang(Double chicangliang) {
    this.chicangliang = chicangliang;
  }


}
