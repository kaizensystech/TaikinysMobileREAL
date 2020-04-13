package java.com.kaizenmax.taikinys_icl.pojo;

public class FarmerDetailsPojo {
    public static final String FARMERDETAILS_TABLE_NAME = "farmerdetails";
    public static final String FARMERDETAILS_COLUMN_ID = "id";
    public static final String FARMERDETAILS_COLUMN_FARMER_NAME = "farmername";
    public static final String FARMERDETAILS_COLUMN_FARMER_LAND = "farmerland";
    public static final String FARMERDETAILS_COLUMN_FARMER_MOBILE = "farmermobile";
    public static final String FARMERDETAILS_COLUMN_PROMO_FM_ID = "promofmid";
    public static final String FARMERDETAILS_COLUMN_PROMO_DEMOL3Serial_ID = "demol3serialid";




   private String farmerName;
   private String farmerLand;
   private String farmerMobile;


    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFarmerLand() {
        return farmerLand;
    }

    public void setFarmerLand(String farmerLand) {
        this.farmerLand = farmerLand;
    }

    public String getFarmerMobile() {
        return farmerMobile;
    }

    public void setFarmerMobile(String farmerMobile) {
        this.farmerMobile = farmerMobile;
    }
}
