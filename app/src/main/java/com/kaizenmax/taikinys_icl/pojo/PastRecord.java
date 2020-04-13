package com.kaizenmax.taikinys_icl.pojo;

import java.util.Date;

public class PastRecord  {

    private String header_1;
    private String header_2;
    private String header_3;
    private String header_4;
    private String header_5;


    private Integer id;
    private String choose_activity;
    private String date_of_activity;
    private Date dateofActivity;
    private String village;
    private String district;
    private String crop_category;
    private String village_formatted;
    private String district_formatted;
    private String crop_category_formatted;
    private String crop_category_focus;
    private String focus_product;
    private String observation;
    private String expenses;
    private String farmerName;
    private String farmermobile;
    private String farmermobile_formatted;
    private String farmerland;
    private String retailerfirm;
    private String retailername;
    private String retailerphn;
    private String row_id;



    public PastRecord() {
    }
/*
    public PastRecord(Integer id,String choose_activity,String date_of_activity,String village,String district,String crop_category,String farmerName,String farmermobile, String row_id) {
        this.id = id;
        this.choose_activity = choose_activity;
        this.date_of_activity = date_of_activity;
        this.village = village;
        this.district = district;
        this.crop_category = crop_category;
        this.farmerName = farmerName;
        this.farmermobile = farmermobile;
        this.row_id = row_id;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChoose_activity() {
        return choose_activity;
    }

    public void setChoose_activity(String choose_activity) {
        this.choose_activity = choose_activity;
    }

    public String getDate_of_activity() {
        return date_of_activity;
    }

    public void setDate_of_activity(String date_of_activity) {
        this.date_of_activity = date_of_activity;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCrop_category() {
        return crop_category;
    }

    public void setCrop_category(String crop_category) {
        this.crop_category = crop_category;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFarmermobile() {
        return farmermobile;
    }

    public void setFarmermobile(String farmermobile) {
        this.farmermobile = farmermobile;
    }

    public String getCrop_category_focus() {
        return crop_category_focus;
    }

    public void setCrop_category_focus(String crop_category_focus) {
        this.crop_category_focus = crop_category_focus;
    }

    public String getFocus_product() {
        return focus_product;
    }

    public void setFocus_product(String focus_product) {
        this.focus_product = focus_product;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public String getFarmerland() {
        return farmerland;
    }

    public void setFarmerland(String farmerland) {
        this.farmerland = farmerland;
    }

    public String getRetailerfirm() {
        return retailerfirm;
    }

    public void setRetailerfirm(String retailerfirm) {
        this.retailerfirm = retailerfirm;
    }

    public String getRetailername() {
        return retailername;
    }

    public void setRetailername(String retailername) {
        this.retailername = retailername;
    }

    public String getRetailerphn() {
        return retailerphn;
    }

    public void setRetailerphn(String retailerphn) {
        this.retailerphn = retailerphn;
    }

    public String getVillage_formatted() {
        return village_formatted;
    }

    public void setVillage_formatted(String village_formatted) {
        this.village_formatted = village_formatted;
    }

    public String getDistrict_formatted() {
        return district_formatted;
    }

    public void setDistrict_formatted(String district_formatted) {
        this.district_formatted = district_formatted;
    }

    public String getCrop_category_formatted() {
        return crop_category_formatted;
    }

    public void setCrop_category_formatted(String crop_category_formatted) {
        this.crop_category_formatted = crop_category_formatted;
    }

    public String getFarmermobile_formatted() {
        return farmermobile_formatted;
    }

    public void setFarmermobile_formatted(String farmermobile_formatted) {
        this.farmermobile_formatted = farmermobile_formatted;
    }

    public Date getDateofActivity() {
        return dateofActivity;
    }

    public void setDateofActivity(Date dateofActivity) {
        this.dateofActivity = dateofActivity;
    }

    public String getRow_id() {
        return row_id;
    }

    public void setRow_id(String row_id) {
        this.row_id = row_id;
    }

    public String getHeader_1() {
        return header_1;
    }

    public void setHeader_1(String header_1) {
        this.header_1 = header_1;
    }

    public String getHeader_2() {
        return header_2;
    }

    public void setHeader_2(String header_2) {
        this.header_2 = header_2;
    }

    public String getHeader_3() {
        return header_3;
    }

    public void setHeader_3(String header_3) {
        this.header_3 = header_3;
    }

    public String getHeader_4() {
        return header_4;
    }

    public void setHeader_4(String header_4) {
        this.header_4 = header_4;
    }

    public String getHeader_5() {
        return header_5;
    }

    public void setHeader_5(String header_5) {
        this.header_5 = header_5;
    }

    @Override
    public String toString(){
        return this.getFarmerName() + "  " + this.getFarmermobile() + this.getChoose_activity() + "  " + this.getCrop_category() + this.getDate_of_activity() + "  " + this.getCrop_category();
    }
}