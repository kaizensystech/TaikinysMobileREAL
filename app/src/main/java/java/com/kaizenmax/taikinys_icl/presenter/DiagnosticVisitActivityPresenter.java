package java.com.kaizenmax.taikinys_icl.presenter;

import android.content.Context;
import android.content.SharedPreferences;


import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.presenter.DiagnosticVisitActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.util.SharedPreferenceUtil;
import com.kaizenmax.taikinys_icl.view.DiagnosticVisitActivity;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticVisitActivityPresenter implements DiagnosticVisitActivityPresenterInterface {

    MobileDatabase dbHelper =new MobileDatabase(DiagnosticVisitActivity.getInstance());

    @Override
    public List<String> getFirmNameList() throws Exception {
        List<String> firmList = dbHelper.getFirmNameList();

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
    public List<String> getProblemCategories() throws Exception {
        List<String > problemCategoriesList=   dbHelper.getProblemCategories();
        if(problemCategoriesList==null )
        {
            problemCategoriesList = new ArrayList<String>();
        }
        return problemCategoriesList;
    }

    @Override
    public List<String> getProblemSubCategoryList(String selectedProblemCategory) throws Exception {
        List<String > problemSubCategoryList=   dbHelper.getProblemSubCategoryList(selectedProblemCategory);
       // List<String > problemSubCategoryList=   new ArrayList<String>();
        return problemSubCategoryList;
    }

    @Override
    public String getFaOfficeDistrict() throws Exception {
        String faOfficeDistrict=dbHelper.getFaOfficeDistrict();
        return faOfficeDistrict;
    }

    @Override
    public String getMobileFromSharedpreference() throws Exception {

        String createdby="";
        SharedPreferences sharedpreferences = DiagnosticVisitActivity.getInstance().getSharedPreferences(SharedPreferenceUtil.PREFERENCES, Context.MODE_PRIVATE);




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
    public boolean insertDVdata(String activityName, String dateOfActivity_entered,
                                String farmerName_entered, String farmerMobile_entered,
                                String village_entered, String selectedCropCategory,
                                String selectedCropFocus, String selectedProblemCategory,
                                String selectedProblemSubCategory, String selectedMeetingPurpose, String nextVisitDate,
                                String cropStage_entered, String expense_entered, String problemDescription_entered,
                                String recommendation_entered, String instructionsDose_entered,
                                String description_entered, List<byte[]> attachmentList,
                                List<RetailerDetailsPojo> retailerDetailsPojoList, String uploadFlagStatus,
                                String createdOn_string, String createdby, String clientName, String faOfficeDistrict) {
Boolean b = dbHelper.insertDVdata(activityName,dateOfActivity_entered,
        farmerName_entered, farmerMobile_entered,
        village_entered,selectedCropCategory,
        selectedCropFocus,selectedProblemCategory,
        selectedProblemSubCategory,selectedMeetingPurpose, nextVisitDate,
        cropStage_entered, expense_entered, problemDescription_entered,
        recommendation_entered, instructionsDose_entered,
        description_entered,attachmentList,
                retailerDetailsPojoList, uploadFlagStatus,
                createdOn_string, createdby, clientName, faOfficeDistrict);

return b;
    }
}
