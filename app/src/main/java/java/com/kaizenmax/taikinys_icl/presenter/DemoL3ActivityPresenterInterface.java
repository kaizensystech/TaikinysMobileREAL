package java.com.kaizenmax.taikinys_icl.presenter;

import android.database.Cursor;


import com.kaizenmax.taikinys_icl.pojo.FarmerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.util.DemoL3ListItem;

import java.util.List;

public interface DemoL3ActivityPresenterInterface {
    public List<String> getVillageList() throws Exception;

    public List<String> getFirmNameList() throws Exception;

    public String getPropName(String toString) throws Exception;

    public String getRetailerMobile(String toString) throws Exception;

    public String getClientName() throws  Exception;

    public String getStateName() throws Exception;

    public List<String> getProductFocusList(String clientName, String faOfficeState) throws Exception;

    public List<String> getCropCategories() throws Exception;

    public List<String> getCropFocus(String selectedCropCategory) throws Exception;

    public List<String> getMeetingPurposeList(String demol3) throws Exception;

    public String getFaOfficeDistrict() throws Exception;

    public String getMobileFromSharedpreference() throws Exception;

   public void insertDemoL3FarmerDetailsdata(String demoL3SerialId, String dateOfActivity_entered, String farmerName_entered, String faOfficeDistrict,
                                             String farmerMobile_entered, String farmerLandAcres_entered, String village_entered,
                                             String createdOn_string, String createdby, String clientName, String uploadFlagStatus,
                                             List<RetailerDetailsPojo> retailerDetailsPojoList, List<byte[]> attachmentList,
                                             String modifyDate_string, Integer stage) throws Exception;



    public Cursor getAllEntriesFromDemoL3() throws Exception;

    public String getStage(Integer demoSerialId) throws Exception;


    public Cursor getDemoL3DataFromID(Integer demoL3SerialId) throws Exception;

    public FarmerDetailsPojo getFarmerDetailsForDemoL3(String demoL3SerialId) throws Exception;

    public List<RetailerDetailsPojo> getRetailerDetailsListForDemoL3(String demoL3SerialId) throws Exception;

    public List<String> getSelectedProductList(Integer demoL3SerialId) throws Exception;

    public  List<DemoL3ListItem> getAll_Incompleted_DemoL3Data() throws Exception;
}
