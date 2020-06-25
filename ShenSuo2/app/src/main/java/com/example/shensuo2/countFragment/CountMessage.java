package com.example.shensuo2.countFragment;

public class CountMessage {
    private String dataID;
    private String platformNaem;
    private String countName;
    private String password;
    private int resourceID;
    private int imageBgOrColorBg;

    public CountMessage(String dataID, String platformNaem, String countName, String password, int resourceID, int imageBgOrColorBg){
        this.platformNaem = platformNaem;
        this.countName = countName;
        this.password = password;
        this.resourceID = resourceID;
        this.dataID = dataID;
        this.imageBgOrColorBg = imageBgOrColorBg;
    }

    public String getDataID() {
        return dataID;
    }

    public void setDataID(String dataID) {
        this.dataID = dataID;
    }

    public String getPlatformNaem() {
        return platformNaem;
    }

    public void setPlatformNaem(String platformNaem) {
        this.platformNaem = platformNaem;
    }

    public String getCountName() {
        return countName;
    }

    public void setCountName(String countName) {
        this.countName = countName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public int getImageBgOrColorBg() {
        return imageBgOrColorBg;
    }

    public void setImageBgOrColorBg(int imageBgOrColorBg) {
        this.imageBgOrColorBg = imageBgOrColorBg;
    }


}
