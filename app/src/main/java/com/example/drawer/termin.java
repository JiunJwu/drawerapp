package com.example.drawer;

public class termin {
    private int id;
    private String name;
    public termin(){super();}
    public termin(int id, String name){
        super();
        this.id=id;
        this.name=name;
    }
    public String getName(){ return name;}
    public int getId(){return id;}
    public void setName(String name){this.name=name;}
    public void setId(int id){this.id=id;}
}
