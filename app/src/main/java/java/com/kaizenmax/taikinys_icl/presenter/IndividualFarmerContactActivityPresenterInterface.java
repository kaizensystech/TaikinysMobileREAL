package java.com.kaizenmax.taikinys_icl.presenter;


import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;

import java.util.List;

public interface IndividualFarmerContactActivityPresenterInterface {


   public List<String> getFirmNameList() throws Exception;

   public String getPropName(String firmName) throws Exception;

   public String getRetailerMobile(String firmName) throws Exception;

   public String getClientName() throws  Exception;

   public String getStateName() throws Exception;

   public List<String> getProductFocusList(String clientName, String faOfficeState) throws Exception;

   public List<String> getVillageList() throws Exception;

   public List<String> getCropCategories() throws Exception;

   public List<String> getCropFocus(String selectedCropCategory) throws Exception;

   public String getFaOfficeDistrict() throws Exception;

   public String getMobileFromSharedpreference() throws  Exception;

   public void insertIFCdata(String ifc, String dateOfActivity_entered, String villageName_entered, String cropFocus_entered, String cropCategory_entered, String farmerName_entered, String farmerMobile_entered, String landAcres_entered, String expenses_entered, String observations_entered, String uploadFlagStatus, List<RetailerDetailsPojo> retailerDetailsPojoList, List<String> selectedProductsList, String createdOn_string, String createdby, String clientName, String faOfficeDistrict) throws Exception;

   public void sendingDataToWebService() throws Exception;
}
