package com.kaizenmax.taikinys_icl.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.util.SharedPreferenceUtil;
import com.kaizenmax.taikinys_icl.view.MandiCampaignActivity;

import java.util.List;

public class MandiCampaignActivityPresenter implements MandiCampaignActivityPresenterInterface {
    MobileDatabase dbHelper =new MobileDatabase(MandiCampaignActivity.getInstance());
    @Override
    public String getClientName() throws Exception {
        String clientName = dbHelper.getClientName();

        return clientName;
    }

    @Override
    public String getStateName() throws Exception {
        String faOfficeState=dbHelper.getStateName();
        return faOfficeState;
    }

    @Override
    public List<String> getProductFocusList(String clientName, String faOfficeState) throws Exception {
        List<String> productlist=dbHelper.getProductFocusList(clientName,faOfficeState);
        return productlist;
    }

    @Override
    public List<String> getCropCategories() throws Exception {
        List<String> cropCategories=dbHelper.getCropCategories();
        return  cropCategories;
    }

    @Override
    public List<String> getCropFocus(String selectedCropCategory) throws Exception {
        List<String > cropFocusList =   dbHelper.getCropFocus(selectedCropCategory);
        return cropFocusList;
    }

    @Override
    public List<String> getMeetingPurposeList(String mandiCampaignActivityName) throws Exception {
        List<String > meetingPurposeList =   dbHelper.getMeetingPurposeList(mandiCampaignActivityName);

        if(meetingPurposeList!=null)
        meetingPurposeList.set(0,"Select Campaign Purpose*");

       // Toast.makeText(MandiCampaignActivity.getInstance(), "Campaign Purpose "+meetingPurposeList.get(0), Toast.LENGTH_SHORT).show();

        return meetingPurposeList;
    }

    @Override
    public List<String> getFirmNameList() throws Exception {
        List<String> firmList= dbHelper.getFirmNameList();

        return firmList;
    }

    @Override
    public String getPropName(String firmName) throws Exception {
        String propName= dbHelper.getPropName(firmName);

        return propName;
    }

    @Override
    public String getRetailerMobile(String firmName) throws Exception {
        String retailerMobile=dbHelper.getRetailerMobile(firmName);

        return retailerMobile;
    }

    @Override
    public String getFaOfficeDistrict() throws Exception {
        String faOfficeDistrict=dbHelper.getFaOfficeDistrict();
        return faOfficeDistrict;
    }

    @Override
    public String getMobileFromSharedpreference() throws Exception {

        String createdby="";
        SharedPreferences sharedpreferences = MandiCampaignActivity.getInstance().getSharedPreferences(SharedPreferenceUtil.PREFERENCES, Context.MODE_PRIVATE);




        if(sharedpreferences!=null)
        {
            if(sharedpreferences.getString(SharedPreferenceUtil.MOBILEKEY,null)==null)
            {


            }
            else
            {

                createdby=sharedpreferences.getString(SharedPreferenceUtil.MOBILEKEY,null);
                //   Toast.makeText(IndividualFarmerContactActivity.this, "mob "+createdby+" createdOn "+createdOn
                //         +" district "+faOfficeDistrict
                //       +"clientName "+clientName, Toast.LENGTH_SHORT).show();
            }

        }
        return createdby;
    }

    @Override
    public void insertMCdata(String dateOfActivity_entered, String mandiName_entered, String faOfficeDistrict,
                             String selectedCropCategory, String selectedCropFocus, String expenses_entered,
                             String selectedMeetingPurpose, String activitySummary_entered, String createdOn,
                             String createdby, String clientName, String uploadFlagStatus,
                             List<RetailerDetailsPojo> retailerDetailsPojoList, List<String> selectedProductsList,
                             List<byte[]> attachmentList) throws Exception {

        dbHelper.insertMCdata( dateOfActivity_entered,  mandiName_entered, faOfficeDistrict,
                 selectedCropCategory,  selectedCropFocus,  expenses_entered,
                 selectedMeetingPurpose,  activitySummary_entered,  createdOn,
                 createdby,  clientName,  uploadFlagStatus,
                retailerDetailsPojoList,  selectedProductsList, attachmentList);
    }
}
