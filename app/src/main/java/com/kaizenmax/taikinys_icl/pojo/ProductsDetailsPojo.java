package com.kaizenmax.taikinys_icl.pojo;

public class ProductsDetailsPojo {
    public static final String PRODUCTDETAILS_TABLE_NAME = "productdetails";
    public static final String PRODUCTDETAILS_COLUMN_ID = "id";
    public static final String PRODUCTDETAILS_COLUMN_PRODUCT_NAME = "productname";
    public static final String PRODUCTDETAILS_COLUMN_PROMO_FM_ID = "promofmid";
    public static final String PRODUCTDETAILS_COLUMN_PROMO_MC_ID = "promomcid";
    public static final String PRODUCTDETAILS_COLUMN_PROMO_DEMOL3Serial_ID = "demol3serialid";




    String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
