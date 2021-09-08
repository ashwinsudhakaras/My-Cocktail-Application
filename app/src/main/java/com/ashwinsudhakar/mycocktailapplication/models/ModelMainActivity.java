package com.ashwinsudhakar.mycocktailapplication.models;

public class ModelMainActivity {

    private String imageUrl;
    private String pName;
    private String sInstructions;
    private String strGlass;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getsInstructions() {
        return sInstructions;
    }

    public void setsInstructions(String sInstructions) {
        this.sInstructions = sInstructions;
    }

    public String getStrGlass() {
        return strGlass;
    }

    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
    }

    public ModelMainActivity(String imageUrl, String pName, String sInstructions, String strGlass) {
        this.imageUrl = imageUrl;
        this.pName = pName;
        this.sInstructions = sInstructions;
        this.strGlass = strGlass;
    }
}
