package com.kaizenmax.taikinys_icl.pojo;

public class RetailerDetailsPojo {
    public static final String RETAILERDETAILS_TABLE_NAME = "retailerdetails";
    public static final String RETAILERDETAILS_COLUMN_ID = "id";
    public static final String RETAILERDETAILS_COLUMN_FIRM_NAME = "firmname";
    public static final String RETAILERDETAILS_COLUMN_PROPRIETOR_NAME = "proprietorname";
    public static final String RETAILERDETAILS_COLUMN_RETAILER_MOBILE = "retailermobile";
    public static final String RETAILERDETAILS_COLUMN_PROMO_FM_ID = "promofmid";
    public static final String RETAILERDETAILS_COLUMN_PROMO_MC_ID = "promomcid";
    public static final String RETAILERDETAILS_COLUMN_PROMO_DEMOL3Serial_ID = "demol3serialid";


    private String firmName;
    private String  propName;
    private String retailerMobile;


    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getRetailerMobile() {
        return retailerMobile;
    }

    public void setRetailerMobile(String retailerMobile) {
        this.retailerMobile = retailerMobile;
    }
}
