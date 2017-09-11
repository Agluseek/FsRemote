package com.agluseek.farsoon.farsoononline.model;

/**
 * Created by Farsoon on 2017/7/18.
 */

public class machines {
    private int ID;
    private String Name;
    private String Type;
    private String SoftwareVar;
    private int AlarmSum;
    private int WarnSum;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSoftwareVar() {
        return SoftwareVar;
    }

    public void setSoftwareVar(String softwareVar) {
        SoftwareVar = softwareVar;
    }

    public int getAlarmSum() {
        return AlarmSum;
    }

    public void setAlarmSum(int alarmSum) {
        AlarmSum = alarmSum;
    }

    public int getWarnSum() {
        return WarnSum;
    }

    public void setWarnSum(int warnSum) {
        WarnSum = warnSum;
    }
}
