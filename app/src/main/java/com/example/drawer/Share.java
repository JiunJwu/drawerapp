package com.example.drawer;

public class Share {
    private int id;
    private int image;
    private String name;

    public Share(){super();}
    public Share(int id, int image, String name){
        super();
        this.id=id;
        this.image=image;
        this.name=name;
    }
    public int getImage(){return image;}
    public String getName(){return name;}
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public void setName(String name){this.name=name;}
    public void setImage(int image){this.image=image;}
}
