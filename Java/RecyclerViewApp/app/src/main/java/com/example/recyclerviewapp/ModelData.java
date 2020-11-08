package com.example.recyclerviewapp;

public class ModelData {

    private String name;
    private int userImage;

    public ModelData() {}

    public ModelData(String name, int userImage) {
        this.name = name;
        this.userImage = userImage;
    }

    public String getName() {
        return name;
    }

    public int getUserImage() {
        return userImage;
    }
}
