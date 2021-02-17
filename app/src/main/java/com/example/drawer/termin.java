package com.example.drawer;

public class termin {
    private int id;
    private String name;
    private String type;
    private String time;
    private String note;
    public termin(){super();}
    public termin(int id, String name,String type,String time,String note){
        super();
        this.id=id;
        this.name=name;
        this.note=note;
        this.type=type;
        this.time=time;
    }
    public String getName(){ return name;}
    public int getId(){return id;}
    public void setName(String name){this.name=name;}
    public void setId(int id){this.id=id;}
    public void setType(String type){this.type=type;}
    public void setTime(String time){this.time=time;}
    public void setNote(String note){this.note=note;}
    public String getType(){return type;}
    public String getTime(){return time;}
    public String getNote(){return note;}
}
