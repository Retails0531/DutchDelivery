package com.example.myapplication;

public class Store {
    private String storeTitle;
    private int image;


    public Store(String title, int id){
        this.image = id;
        this.storeTitle = title;
    }

    public String getStoreTitle(){
        return storeTitle;
    }

    public void setStoreTitle(String storeTitle){
        this.storeTitle = storeTitle;
    }

    public int getImage(){
        return image;
    }
    public void setImage(int image){
        this.image = image;
    }
}
