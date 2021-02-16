package com.example.drawer;

import java.io.Serializable;

public class detail_setting implements Serializable {
    private Boolean sec1goto,sec2goto,sec3goto;
    private int id,raw;
    private String name;
    public detail_setting(){super();}
    public detail_setting(int id,Boolean sec1goto,Boolean sec2goto,Boolean sec3goto, String name,int raw){
        this.id=id;
        this.sec1goto=sec1goto;
        this.sec2goto=sec2goto;
        this.sec3goto=sec3goto;
        this.name=name;
        this.raw=raw;
    }
    public String getName(){ return name;}
    public Boolean getSec1goto(){return sec1goto;}
    public Boolean getSec2goto(){return sec2goto;}
    public Boolean getSec3goto(){return sec3goto;}
    public void setName(String name){this.name=name;}
    public void setSec1goto(Boolean sec1goto){this.sec1goto=sec1goto;}
    public void setSec2goto(Boolean sec2goto){this.sec2goto=sec2goto;}
    public void setSec3goto(Boolean sec3goto){this.sec3goto=sec3goto;}
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public void setRaw(int raw){this.raw=raw;}
    public int getRaw(){return raw;}
}
