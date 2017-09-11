package com.agluseek.farsoon.farsoononline.model;

/**
 * Created by Farsoon on 2017/4/27.
 */

public class Individual_Settings {
    private int iv;
    private String msg;

    public Individual_Settings(int iv, String msg) {
        this.iv = iv;
        this.msg = msg;
    }

    public int getIv() {
        return iv;
    }

    public void setIv(int iv) {
        this.iv = iv;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
