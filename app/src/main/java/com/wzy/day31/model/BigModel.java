package com.wzy.day31.model;

import java.util.List;

/**
 * Created by zy on 2016/3/28.
 */
public class BigModel {
    private List<Model> data;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Model> getData() {
        return data;
    }

    public void setData(List<Model> data) {
        this.data = data;
    }
}
