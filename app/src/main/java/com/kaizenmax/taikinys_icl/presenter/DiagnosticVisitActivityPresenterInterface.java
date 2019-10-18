package com.kaizenmax.taikinys_icl.presenter;


import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;

import java.util.List;

public interface DiagnosticVisitActivityPresenterInterface {
    public List<String> getFirmNameList() throws Exception;
    
    public  String getPropName(String toString) throws Exception;

    public  String getRetailerMobile(String toString) throws Exception;

    public String getClientName() throws Exception;

    public String getStateName() throws Exception;

    public List<String> getVillageList() throws Exception;

    public List<String> getCropCategories() throws Exception;

    public List<String> getCropFocus(String selectedCropCategory) throws Exception;

    public List<String> getMeetingPurposeList(String farmerMeeting) throws Exception;

    public List<String> getProblemCategories() throws Exception;

    public List<String> getProblemSubCategoryList(String selectedProblemCategory) throws Exception;

    public String getFaOfficeDistrict() throws Exception;

    public String getMobileFromSharedpreference() throws Exception;

    public boolean insertDVdata(String activityName, String dateOfActivity_entered, String farmerName_entered,
                                String farmerMobile_entered, String village_entered, String selectedCropCategory,
                                String selectedCropFocus, String selectedProblemCategory, String selectedProblemSubCategory,
                                String selectedMeetingPurpose, String nextVisitDate_entered, String cropStage_entered,
                                String expense_entered, String problemDescription_entered, String recommendation_entered,
                                String instructionsDose_entered, String description_entered, List<byte[]> attachmentList,
                                List<RetailerDetailsPojo> retailerDetailsPojoList, String uploadFlagStatus,
                                String createdOn_string, String createdby, String clientName, String faOfficeDistrict) throws Exception;
}
