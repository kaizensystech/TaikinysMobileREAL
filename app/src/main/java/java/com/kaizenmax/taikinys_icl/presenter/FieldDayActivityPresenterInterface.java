package java.com.kaizenmax.taikinys_icl.presenter;



import com.kaizenmax.taikinys_icl.pojo.FarmerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;

import java.util.List;

public interface FieldDayActivityPresenterInterface {
    public String getClientName() throws  Exception;

    public String getStateName() throws  Exception;

    public List<String> getProductFocusList(String clientName, String faOfficeState) throws Exception;

    public List<String> getVillageList() throws Exception;

    public List<String> getCropCategories() throws  Exception;

    public List<String> getCropFocus(String selectedCropCategory) throws Exception;

    public List<String> getMeetingPurposeList(String activityName) throws Exception;

    public String getPropName(String toString) throws Exception;

    public String getRetailerMobile(String toString) throws Exception;

    public List<String> getFirmNameList() throws Exception;

    public String getFaOfficeDistrict() throws Exception;

    public String getMobileFromSharedpreference() throws Exception;

    public void insertFDdata(String activityName, String dateOfActivity,
                             String noOfFarmers_entered, String expenses_entered,
                             String village_entered, String selectedCropCategory, String selectedCropFocus,
                             String selectedMeetingPurpose, List<String> selectedProductsList,
                             String observations, List<FarmerDetailsPojo> farmerDetailsPojoList,
                             List<RetailerDetailsPojo> retailerDetailsPojoList,
                             String uploadFlagStatus,
                             String createdOn_string, String createdby, String clientName, String faOfficeDistrict, List<byte[]> attachmentList)
            throws Exception;

}
