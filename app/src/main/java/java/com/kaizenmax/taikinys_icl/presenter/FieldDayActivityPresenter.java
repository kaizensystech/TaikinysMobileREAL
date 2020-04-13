package java.com.kaizenmax.taikinys_icl.presenter;

import android.content.Context;
import android.content.SharedPreferences;


import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.pojo.FarmerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.presenter.FieldDayActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.util.SharedPreferenceUtil;
import com.kaizenmax.taikinys_icl.view.FieldDayActivity;

import java.util.List;

public class FieldDayActivityPresenter implements FieldDayActivityPresenterInterface {
    MobileDatabase dbHelper =new MobileDatabase(FieldDayActivity.getInstance());

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
    public List<String> getVillageList() throws Exception {
        List<String> villageList=dbHelper.getVillageList();
        return villageList;
    }

    @Override
    public List<String> getCropCategories() throws Exception {
        List<String> cropCategories=dbHelper.getCropCategories();
        return  cropCategories;
    }

    @Override
    public List<String> getCropFocus(String selectedCropCategory) throws Exception {
        List<String > cropFocusList=   dbHelper.getCropFocus(selectedCropCategory);
        return cropFocusList;
    }

    @Override
    public List<String> getMeetingPurposeList(String activityName) throws Exception {
        List<String > meetingPurposeList=   dbHelper.getMeetingPurposeList(activityName);
        return meetingPurposeList;
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
    public List<String> getFirmNameList() throws Exception {
        List<String> firmList= dbHelper.getFirmNameList();

        return firmList;
    }

    @Override
    public String getFaOfficeDistrict() throws Exception {
        String faOfficeDistrict=dbHelper.getFaOfficeDistrict();
        return faOfficeDistrict;
    }

    @Override
    public String getMobileFromSharedpreference() throws Exception {

        String createdby="";
        SharedPreferences sharedpreferences = FieldDayActivity.getInstance().getSharedPreferences(SharedPreferenceUtil.PREFERENCES, Context.MODE_PRIVATE);




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
    public void insertFDdata(String activityName, String dateOfActivity, String noOfFarmers_entered,
                             String expenses_entered, String village_entered, String selectedCropCategory,
                             String selectedCropFocus, String selectedMeetingPurpose, List<String> selectedProductsList,
                             String observations, List<FarmerDetailsPojo> farmerDetailsPojoList,
                             List<RetailerDetailsPojo> retailerDetailsPojoList, String uploadFlagStatus,
                             String createdOn_string, String createdby, String clientName, String faOfficeDistrict,
                             List<byte[]> attachmentList) throws Exception {

        dbHelper.insertFDdata( activityName,  dateOfActivity,  noOfFarmers_entered,
                expenses_entered,  village_entered,
                selectedCropCategory,  selectedCropFocus,
                selectedMeetingPurpose,  selectedProductsList,
                observations,  farmerDetailsPojoList,
                retailerDetailsPojoList,  uploadFlagStatus,
                createdOn_string,  createdby,  clientName,
                faOfficeDistrict, attachmentList);

    }
}
