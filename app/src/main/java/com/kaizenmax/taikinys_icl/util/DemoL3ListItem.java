package com.kaizenmax.taikinys_icl.util;

public class DemoL3ListItem {
    private String dateOfActivity;
    private String lastUpdatedOn;
    private String demoL3TempId;
    private String demoL3PermanentId;
    private String farmerName;
    private String villageName;

  /* public DemoL3ListItem()
    {

    } */

    public DemoL3ListItem(  String dateOfActivity, String lastUpdatedOn, String demoL3TempId,
            String demoL3PermanentId, String farmerName, String villageName) {
        this.dateOfActivity = dateOfActivity;
        this.lastUpdatedOn = lastUpdatedOn;
        this.demoL3TempId = demoL3TempId;
        this.demoL3PermanentId = demoL3PermanentId;
        this.farmerName = farmerName;
        this.villageName = villageName;
    }

    public String getDateOfActivity() {
        return dateOfActivity;
    }

    public void setDateOfActivity(String dateOfActivity) {
        this.dateOfActivity = dateOfActivity;
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public String getDemoL3TempId() {
        return demoL3TempId;
    }

    public void setDemoL3TempId(String demoL3TempId) {
        this.demoL3TempId = demoL3TempId;
    }

    public String getDemoL3PermanentId() {
        return demoL3PermanentId;
    }

    public void setDemoL3PermanentId(String demoL3PermanentId) {
        this.demoL3PermanentId = demoL3PermanentId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
}
