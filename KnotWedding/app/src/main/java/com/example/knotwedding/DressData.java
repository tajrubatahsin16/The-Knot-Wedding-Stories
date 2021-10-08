package com.example.knotwedding;

public class DressData {
    private String drName;
    private String drDes;
    private String drCost;
    private String drImage;

    public DressData()
    {

    }

    public DressData(String drName, String drDes, String drCost, String drImage) {
        this.drName = drName;
        this.drDes = drDes;
        this.drCost = drCost;
        this.drImage = drImage;
    }

    public String getDrName() {
        return drName;
    }

    public String getDrDes() {
        return drDes;
    }

    public String getDrCost() {
        return drCost;
    }

    public String getDrImage() {
        return drImage;
    }
}
