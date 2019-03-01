package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by li on 2019/3/1 0001.
 */

public class Province  extends DataSupport{
    private  int id;
    private  String provinceName;
    private  int provinceCode;
    public int getId(){
        return id;
    }
    public void setid(int id){
        this.id = id;
    }
    public String getProvinceName(){
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
