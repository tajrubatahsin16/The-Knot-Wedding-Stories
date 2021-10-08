package com.example.knotwedding;

public class VenueData {
    private String venName;
    private String venDes;
    private String venCost;
    private String venImage;

    public  VenueData(){

    }

    public VenueData(String venName, String venDes, String venCost, String venImage) {
        this.venName = venName;
        this.venDes = venDes;
        this.venCost = venCost;
        this.venImage = venImage;
    }

    public String getVenName() {
        return venName;
    }

    public String getVenDes() {
        return venDes;
    }

    public String getVenCost() {
        return venCost;
    }

    public String getVenImage() {
        return venImage;
    }
}
