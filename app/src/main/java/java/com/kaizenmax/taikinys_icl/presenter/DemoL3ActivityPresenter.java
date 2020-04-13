package java.com.kaizenmax.taikinys_icl.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;


import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.pojo.FarmerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.util.DemoL3ListItem;
import com.kaizenmax.taikinys_icl.util.SharedPreferenceUtil;
import com.kaizenmax.taikinys_icl.view.DemoL3Activity;
import com.kaizenmax.taikinys_icl.view.DemoL3_InProgressActivity;

import java.util.List;

public class DemoL3ActivityPresenter implements DemoL3ActivityPresenterInterface {
    MobileDatabase dbHelper =new MobileDatabase(DemoL3Activity.getInstance());

    @Override
    public List<String> getVillageList() throws Exception {
        List<String> villageList=dbHelper.getVillageList();
        return villageList;
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
        List<String> cropFocusList=   dbHelper.getCropFocus(selectedCropCategory);
        return cropFocusList;
    }

    @Override
    public List<String> getMeetingPurposeList(String activityName) throws Exception {
        List<String > meetingPurposeList=   dbHelper.getMeetingPurposeList(activityName);
        return meetingPurposeList;
    }

    @Override
    public String getFaOfficeDistrict() throws Exception {
        String faOfficeDistrict=dbHelper.getFaOfficeDistrict();
        return faOfficeDistrict;
    }

    @Override
    public String getMobileFromSharedpreference() throws Exception {

        String createdby="";
        SharedPreferences sharedpreferences = DemoL3Activity.getInstance().getSharedPreferences(SharedPreferenceUtil.PREFERENCES, Context.MODE_PRIVATE);




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
    public void insertDemoL3FarmerDetailsdata(String demoL3SerialId, String dateOfActivity_entered, String farmerName_entered,
                                              String faOfficeDistrict, String farmerMobile_entered,
                                              String farmerLandAcres_entered, String village_entered,
                                              String createdOn_string, String createdby, String clientName,
                                              String uploadFlagStatus, List<RetailerDetailsPojo> retailerDetailsPojoList,
                                              List<byte[]> attachmentList, String modifyDate_string, Integer stage) throws Exception {


        dbHelper.insertDemoL3FarmerDetailsdata( demoL3SerialId,dateOfActivity_entered,  farmerName_entered,faOfficeDistrict ,farmerMobile_entered,
                farmerLandAcres_entered,  village_entered,  createdOn_string,
                createdby,  clientName,  uploadFlagStatus, retailerDetailsPojoList,attachmentList, modifyDate_string,
                stage);

    }

    @Override
    public Cursor getAllEntriesFromDemoL3() throws Exception {
        Cursor res=   dbHelper.getAllEntriesFromDemoL3();

        return res;
    }

    @Override
    public String getStage(Integer demoSerialId) throws Exception {
        String stage = dbHelper.getStage(demoSerialId);

        return stage;
    }

    @Override
    public Cursor getDemoL3DataFromID(Integer demoL3SerialId) throws Exception {
        Cursor cursor = dbHelper.getDemoL3DataFromID(demoL3SerialId);

       return cursor;
    }

    @Override
    public FarmerDetailsPojo getFarmerDetailsForDemoL3(String demoL3SerialId) throws Exception {
        FarmerDetailsPojo farmerDetailsPojo = dbHelper.getFarmerDetailsForDemoL3(demoL3SerialId);

        return farmerDetailsPojo;
    }

    @Override
    public List<RetailerDetailsPojo> getRetailerDetailsListForDemoL3(String demoL3SerialId) throws Exception {

        List<RetailerDetailsPojo> retailerDetailsPojoList = dbHelper.getRetailerDetailsListForDemoL3(demoL3SerialId);

        return retailerDetailsPojoList;
    }

    @Override
    public List<String> getSelectedProductList(Integer demoL3SerialId) throws Exception {
        MobileDatabase dbHelper2 =new MobileDatabase(DemoL3_InProgressActivity.getInstance());

        List<String> selectedProductList = dbHelper2.getSelectedProductList(demoL3SerialId);



        return selectedProductList;
    }

    @Override
    public List<DemoL3ListItem> getAll_Incompleted_DemoL3Data() throws Exception {
        MobileDatabase dbHelper2 =new MobileDatabase(DemoL3_InProgressActivity.getInstance());

        List<DemoL3ListItem> list=   dbHelper2.getAll_Incompleted_DemoL3Data();

        return list;
        }
}
