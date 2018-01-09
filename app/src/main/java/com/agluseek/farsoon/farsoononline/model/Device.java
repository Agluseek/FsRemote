package com.agluseek.farsoon.farsoononline.model;

/**
 * Created by Farsoon on 2017/7/31.
 */

public class Device {
    private int AlarmSum;
    private String Type;
    private int WarnSum;
    private String ID;
    private String SoftwareVar;
    private String Name;
    private int NormalMsg;
    private int WarningMsg;
    private int AlarmMsg;
    private int DeviceNumber;

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

    public int getNormalMsg() {
        return NormalMsg;
    }

    public void setNormalMsg(int normalMsg) {
        NormalMsg = normalMsg;
    }

    public int getWarningMsg() {
        return WarningMsg;
    }

    public void setWarningMsg(int warningMsg) {
        WarningMsg = warningMsg;
    }

    public int getAlarmMsg() {
        return AlarmMsg;
    }

    public void setAlarmMsg(int alarmMsg) {
        AlarmMsg = alarmMsg;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSoftwareVar() {
        return SoftwareVar;
    }

    public void setSoftwareVar(String softwareVar) {
        SoftwareVar = softwareVar;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
