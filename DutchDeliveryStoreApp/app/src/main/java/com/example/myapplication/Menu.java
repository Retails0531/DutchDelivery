package com.example.myapplication;

public class Menu {
    String name;
    int price;
    public Menu(String name, int price){
        this.name=name;
        this.price=price;
    }

    public String getName(){
        return name;
    }
    public void setName(){
        this.name=name;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(){
        this.price=price;
    }
}
