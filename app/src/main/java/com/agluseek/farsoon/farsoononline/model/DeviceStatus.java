package com.agluseek.farsoon.farsoononline.model;
/**
 * Created by Farsoon on 2017/8/7.
 */
public class DeviceStatus {
    private String ID;
    private String CurState;
    private String PackageName;
    private String MaterialName;
    private String PackageHeight;
    private String CurHeight;
    private String Temp;
    private String RemainPowder;
    private String CurStage;
    private String RemainTime;


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCurState() {
        return CurState;
    }

    public void setCurState(String curState) {
        CurState = curState;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public String getPackageHeight() {
        return PackageHeight;
    }

    public void setPackageHeight(String packageHeight) {
        PackageHeight = packageHeight;
    }

    public String getCurHeight() {
        return CurHeight;
    }

    public void setCurHeight(String curHeight) {
        CurHeight = curHeight;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }

    public String getRemainPowder() {
        return RemainPowder;
    }

    public void setRemainPowder(String remainPowder) {
        RemainPowder = remainPowder;
    }

    public String getCurStage() {
        return CurStage;
    }

    public void setCurStage(String curStage) {
        CurStage = curStage;
    }

    public String getRemainTime() {
        return RemainTime;
    }

    public void setRemainTime(String remainTime) {
        RemainTime = remainTime;
    }
}
