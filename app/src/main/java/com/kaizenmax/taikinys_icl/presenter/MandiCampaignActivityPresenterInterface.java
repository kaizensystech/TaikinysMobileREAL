package com.kaizenmax.taikinys_icl.presenter;


import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;

import java.util.List;

public interface MandiCampaignActivityPresenterInterface {
    public String getClientName() throws Exception;

    public String getStateName() throws Exception;

    public List<String> getProductFocusList(String clientName, String faOfficeState) throws Exception;

    public List<String> getCropCategories() throws Exception;

    public List<String> getCropFocus(String selectedCropCategory) throws Exception;

    public List<String> getMeetingPurposeList(String mandiCampaignActivityName) throws Exception;

    public List<String> getFirmNameList() throws Exception;

    public String getPropName(String toString) throws Exception;

    public String getRetailerMobile(String toString) throws Exception;

    public String getFaOfficeDistrict() throws Exception;

    public String getMobileFromSharedpreference() throws Exception;

    public void insertMCdata(String dateOfActivity_entered, String mandiName_entered, String faOfficeDistrict, String selectedCropCategory,
                             String selectedCropFocus, String expenses_entered, String selectedMeetingPurpose, String activitySummary_entered,
                             String createdOn, String createdby, String clientName, String uploadFlagStatus, List<RetailerDetailsPojo> retailerDetailsPojoList,
                             List<String> selectedProductsList, List<byte[]> attachmentList) throws Exception;
}
