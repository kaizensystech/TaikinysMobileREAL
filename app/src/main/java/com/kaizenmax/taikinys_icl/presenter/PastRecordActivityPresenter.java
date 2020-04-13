package com.kaizenmax.taikinys_icl.presenter;

import android.database.Cursor;
import android.widget.Toast;

import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.pojo.FarmerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.PastRecord;
import com.kaizenmax.taikinys_icl.pojo.PromoFarmerMeetingPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.view.PastRecordActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class PastRecordActivityPresenter implements PastRecordActivityPresenterInterface {

    MobileDatabase dbHelper;
    PastRecord pastRecord;
    List<PastRecord> pastRecordList= new ArrayList<>();


    public void test(){
        Toast.makeText(PastRecordActivity.getInstance(), "Testing", Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<PastRecord> getAllPromoEntries(){

       // Toast.makeText(PastRecordActivity.getInstance(), "Initiating db query", Toast.LENGTH_SHORT).show();

        final Cursor cursor = dbHelper.getAllPromoFMEntries();

        if(cursor.getCount()==0)
        {
           // getDataSetFromCloudAndLocalSave();
        }
        else{
            int x = 0;
            if (cursor != null && cursor.moveToFirst()) {
                do{
                    Integer id = cursor.getInt(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ID));
                    String choose_activity = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CHOOSE_ACTIVITY));
                    String date_of_activity = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DATE_OF_ACTIVITY));
                    String village = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_VILLAGE));
                    String district = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DISTRICT));
                    String crop_category = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_CATEGORY));
                    x++;

                    String farmerName = null;
                    String farmermobile = null;

                    // List<FarmerDetailsPojo> farmerDetailsPojoList= dbHelper.getFarmerDetails();
                    List<FarmerDetailsPojo> FarmerDetailsPojoList=dbHelper.getFarmerDetails(id);
                    for (int i=0;i<1;i++)
                    {
                        FarmerDetailsPojo farmerDetailsPojo=FarmerDetailsPojoList.get(i);
                        farmerName=farmerDetailsPojo.getFarmerName();
                        farmermobile =farmerDetailsPojo.getFarmerMobile();
                    }

                    /*
                    List<RetailerDetailsPojo> retailerList=dbHelper.getRetailerDetails(id);

                    for (int i=0;i<retailerList.size();i++)
                    {
                        RetailerDetailsPojo  retailerDetailsPojo=retailerList.get(i);

                        //Toast.makeText(instance, "Firm Name "+retailerDetailsPojo.getFirmName(), Toast.LENGTH_SHORT).show();
                    } */

                    pastRecord = new PastRecord();
                    pastRecord.setId(id);
                    pastRecord.setChoose_activity(choose_activity);
                    pastRecord.setDate_of_activity(date_of_activity);
                    pastRecord.setVillage(village);
                    pastRecord.setDistrict(district);
                    pastRecord.setCrop_category(crop_category);
                    pastRecord.setFarmerName(farmerName);
                    pastRecord.setFarmermobile(farmermobile);

                    pastRecordList.add(pastRecord);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }



        return pastRecordList;
    }
}
