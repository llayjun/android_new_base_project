package com.base.base.bean;

import java.io.Serializable;

/**
 * Created by zhangyinlei on 2018/6/28
 */
public class BaseBean<T> implements Serializable {

    private int CODE;
    private T MSG;

    public int getCODE() {
        return CODE;
    }

    public void setCODE(int CODE) {
        this.CODE = CODE;
    }

    public T getMSG() {
        return MSG;
    }

    public void setMSG(T MSG) {
        this.MSG = MSG;
    }
}
