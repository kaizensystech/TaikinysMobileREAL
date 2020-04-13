package java.com.kaizenmax.taikinys_icl.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.kaizenmax.taikinys_icl.pojo.AttachmentsPojo;
import com.kaizenmax.taikinys_icl.pojo.DataSetMasterPojo;
import com.kaizenmax.taikinys_icl.pojo.DemoL3Pojo;
import com.kaizenmax.taikinys_icl.pojo.FaMasterPojo;
import com.kaizenmax.taikinys_icl.pojo.FarmerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.MandiCampaignPojo;
import com.kaizenmax.taikinys_icl.pojo.ProductsDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.PromoFarmerMeetingPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.UsersPojo;
import com.kaizenmax.taikinys_icl.pojo.VillagesPojo;
import com.kaizenmax.taikinys_icl.util.CommonConstants;
import com.kaizenmax.taikinys_icl.util.DemoL3ListItem;
import com.kaizenmax.taikinys_icl.view.DemoL3Activity;
import com.kaizenmax.taikinys_icl.view.DemoL3_InProgressActivity;
import com.kaizenmax.taikinys_icl.view.FarmerMeetingActivity;
import com.kaizenmax.taikinys_icl.view.IndividualFarmerContactMainActivity;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MobileDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MobileDatabase.db";
    private static final int DATABASE_VERSION = 1;


  //  public IndividualFarmerContactActivity activity=new IndividualFarmerContactActivity();

    public MobileDatabase(Context context) {

        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataSetMasterPojo.DATASETMASTER_TABLE_NAME + "(" +
                DataSetMasterPojo.DATASETMASTER_COLUMN_ID + " INTEGER  PRIMARY KEY, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE + " TEXT, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_DESCRIPTION + " TEXT, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT1 + " TEXT, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT2 + " TEXT, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT3 + " TEXT, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT4 + " TEXT, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_NUMBER1 + " INTEGER, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_NUMBER2 + " INTEGER, "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_TEXT1 + " INTEGER, "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_TEXT2 + " INTEGER, "+

                DataSetMasterPojo.DATASETMASTER_COLUMN_DATE1 + " TEXT, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_DATE2 + " TEXT, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS + " TEXT, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_CREATED_ON + " TEXT, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_CREATED_BY + " TEXT, " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_CLIENT_NAME + " TEXT) ");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME + "(" +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ID + " INTEGER  PRIMARY KEY, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CHOOSE_ACTIVITY + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DATE_OF_ACTIVITY + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_NUMBER_OF_FARMER + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_VILLAGE + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DISTRICT + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_CATEGORY + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_FOCUS + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_FOCUS_PRODUCT + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_EXPENSES + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_MEETING_PURPOSE + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ATTACHMENT_FILE + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_LOCATION + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_STATUS + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_STAGE + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CREATED_ON + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CREATED_BY + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ATTACHMENT_FILE_NAME + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_OBSERVATIONS + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CLIENT_NAME + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_STAGE + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_PROBLEM_CATEGORY + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_PROBLEM_SUB_CATEGORY + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_PROBLEM_DESCRIPTION + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_RECOMMENDATION + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_INSTRUCTIONS_DOSE + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_EXPERT_HELP + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DESCRIPTION + " TEXT, " +
                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_UPLOAD_FLAG + " TEXT, " +

                        PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_NEXT_FIELD_VISIT_DATE + " TEXT)" );


        db.execSQL("CREATE TABLE IF NOT EXISTS " + MandiCampaignPojo.MANDICAMPAIGN_TABLE_NAME + "(" +
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_ID + " INTEGER  PRIMARY KEY, " +
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_DATE_OF_ACTIVITY + " TEXT, " +
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_MANDI_NAME + " TEXT, " +
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_DISTRICT + " TEXT, " +
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CROP_CATEGORY + " TEXT, " +
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CROP_FOCUS + " TEXT, " +
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_EXPENSES + " TEXT, " +
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CAMPAIGN_PURPOSE +" TEXT, "+
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_ACTIVITY_SUMMARY +" TEXT, "+
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CREATED_ON + " TEXT, " +
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CREATED_BY + " TEXT, " +
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CLIENT_NAME +" TEXT, "+
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_UPLOAD_FLAG +" TEXT )");



       db.execSQL("CREATE TABLE IF NOT EXISTS " + DemoL3Pojo.DEMOL3_TABLE_NAME + "(" +
                DemoL3Pojo.DEMOL3_COLUMN_ID + " INTEGER  PRIMARY KEY, " +
                DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID + " TEXT, " +
                DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_ACTIVITY + " TEXT, " +
                DemoL3Pojo.DEMOL3_COLUMN_VILLAGE + " TEXT, " +
                DemoL3Pojo.DEMOL3_COLUMN_CROP_CATEGORY + " TEXT, " +
                DemoL3Pojo.DEMOL3_COLUMN_CROP_FOCUS + " TEXT, " +
                DemoL3Pojo.DEMOL3_COLUMN_DEMO_PURPOSE + " TEXT, " +
                DemoL3Pojo.DEMOL3_COLUMN_ACRES +" TEXT, "+
                DemoL3Pojo.DEMOL3_COLUMN_DOSE +" TEXT, "+
                DemoL3Pojo.DEMOL3_COLUMN_INTERIM + " TEXT, " +
                DemoL3Pojo.DEMOL3_COLUMN_YIELD + " TEXT, " +
                DemoL3Pojo.DEMOL3_COLUMN_STAGE +" TEXT, "+
                DemoL3Pojo.DEMOL3_COLUMN_EXPENSES +" TEXT, " +
                DemoL3Pojo.DEMOL3_COLUMN_DATEOFYIELD +" TEXT, " +
                DemoL3Pojo.DEMOL3_COLUMN_STATUS +" TEXT, " +
                DemoL3Pojo.DEMOL3_COLUMN_CREATED_BY +" TEXT, "+
                DemoL3Pojo.DEMOL3_COLUMN_CREATED_ON +" TEXT, "+
                DemoL3Pojo.DEMOL3_COLUMN_CLIENT_NAME +" TEXT, "+
                DemoL3Pojo.DEMOL3_COLUMN_MODIFY_DATE +" TEXT, "+
                DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_PROTOCOL +" TEXT, "+
                DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_EXECUTION +" TEXT, "+
                DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_INTERIM +" TEXT, "+
                DemoL3Pojo.DEMOL3_COLUMN_DISTRICT +" TEXT, "+
                DemoL3Pojo.DEMOL3_COLUMN_UPLOAD_FLAG +" TEXT, " +
               DemoL3Pojo.DEMOL3_COLUMN_PERMANENT_DEMOL3_SERIAL_ID  +" TEXT )");







       /* CREATE TABLE order(
                id INTEGER,
                customerID INTEGER,
                date TEXT,
                FOREIGN KEY(customerId) REFERENCES customer(id)
        ); */




        db.execSQL("CREATE TABLE IF NOT EXISTS " + FarmerDetailsPojo.FARMERDETAILS_TABLE_NAME + "(" +
                FarmerDetailsPojo.FARMERDETAILS_COLUMN_ID + " INTEGER  PRIMARY KEY, " +
                FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_LAND + " TEXT, " +
                FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_NAME + " TEXT, " +
                FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_MOBILE + " TEXT, " +
                FarmerDetailsPojo.FARMERDETAILS_COLUMN_PROMO_FM_ID +" INTEGER, "+
                        FarmerDetailsPojo.FARMERDETAILS_COLUMN_PROMO_DEMOL3Serial_ID +" INTEGER, "+
                "FOREIGN KEY(promofmid) REFERENCES promofarmermeeting(id), "+
                        "FOREIGN KEY(demol3serialid) REFERENCES demol3(demol3serialid) )"

                );


        db.execSQL("CREATE TABLE IF NOT EXISTS " + RetailerDetailsPojo.RETAILERDETAILS_TABLE_NAME + "(" +
                RetailerDetailsPojo.RETAILERDETAILS_COLUMN_ID + " INTEGER  PRIMARY KEY, " +
                RetailerDetailsPojo.RETAILERDETAILS_COLUMN_FIRM_NAME + " TEXT, " +
                RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROPRIETOR_NAME + " TEXT, " +
                RetailerDetailsPojo.RETAILERDETAILS_COLUMN_RETAILER_MOBILE + " TEXT, " +
                RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_FM_ID +" INTEGER, "+
                RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_MC_ID +" INTEGER, "+
                RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_DEMOL3Serial_ID +" INTEGER, "+
                "FOREIGN KEY(promofmid) REFERENCES promofarmermeeting(id), " +
                        "FOREIGN KEY(promomcid) REFERENCES promomandicampaign(id), " +
                "FOREIGN KEY(demol3serialid) REFERENCES demol3(demol3serialid)) "
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS " + ProductsDetailsPojo.PRODUCTDETAILS_TABLE_NAME + "(" +
                ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_ID + " INTEGER  PRIMARY KEY, " +
                ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PRODUCT_NAME + " TEXT, " +
                ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PROMO_FM_ID +" INTEGER, "+
                ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PROMO_MC_ID +" INTEGER, "+
                ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PROMO_DEMOL3Serial_ID +" INTEGER, "+
                "FOREIGN KEY(promofmid) REFERENCES promofarmermeeting(id) ," +
                        "FOREIGN KEY(promomcid) REFERENCES promomandicampaign(id) ,"+
                "FOREIGN KEY(demol3serialid) REFERENCES demol3(demol3serialid))"

        );





        db.execSQL("CREATE TABLE IF NOT EXISTS " + AttachmentsPojo.ATTACHMENTS_TABLE_NAME + "(" +
                AttachmentsPojo.ATTACHMENTS_COLUMN_ID + " INTEGER  PRIMARY KEY, " +
                AttachmentsPojo.ATTACHMENTS_COLUMN_ATTACHMENT_FILE + " BLOB, " +
                AttachmentsPojo.ATTACHMENTS_COLUMN_PROMO_FM_ID +" INTEGER, "+
                AttachmentsPojo.ATTACHMENTS_COLUMN_PROMO_MC_ID +" INTEGER, "+
                AttachmentsPojo.ATTACHMENTS_COLUMN_PROMO_DEMOL3Serial_ID +" INTEGER, "+
                "FOREIGN KEY(promofmid) REFERENCES promofarmermeeting(id) ," +
                        "FOREIGN KEY(promomcid) REFERENCES promomandicampaign(id) ,"+
                "FOREIGN KEY(demol3serialid) REFERENCES demol3(demol3serialid))"
        );




        db.execSQL("CREATE TABLE IF NOT EXISTS " + FaMasterPojo.FAMASTER_TABLE_NAME + "(" +
                FaMasterPojo.FAMASTER_COLUMN_ID + " INTEGER  PRIMARY KEY, " +
                FaMasterPojo.FAMASTER_COLUMN_FA_FIRSTNAME + " TEXT, " +
                FaMasterPojo.FAMASTER_COLUMN_FA_LASTNAME + " TEXT, " +
                FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_TERRITORY +" TEXT, "+
                FaMasterPojo.FAMASTER_COLUMN_FA_CLIENTNAME +" TEXT, "+
                FaMasterPojo.FAMASTER_COLUMN_FA_VALIDITY_FROM +" TEXT, "+
                FaMasterPojo.FAMASTER_COLUMN_FA_VALIDITY_TO +" TEXT, "+
                FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_DISTRICT +" TEXT, "+
                FaMasterPojo.FAMASTER_COLUMN_FA_HEADQUARTER +" TEXT, "+
                FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_REGIONAL_OFFICE +" TEXT, "+
                FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_STATE +" TEXT, "+
                FaMasterPojo.FAMASTER_COLUMN_FA_STATUS +" TEXT) " );

        db.execSQL("CREATE TABLE IF NOT EXISTS " + UsersPojo.USERS_TABLE_NAME + "(" +
                UsersPojo.USERS_COLUMN_ID + " INTEGER  PRIMARY KEY, " +
                UsersPojo.USERS_COLUMN_USERNAME + " TEXT, " +
                UsersPojo.USERS_COLUMN_PASSWORD + " TEXT, " +
                UsersPojo.USERS_COLUMN_OTP +" TEXT, "+
                UsersPojo.USERS_COLUMN_STATUS +" TEXT ) " );

        db.execSQL("CREATE TABLE IF NOT EXISTS " + VillagesPojo.VILLAGES_TABLE_NAME + "(" +
                VillagesPojo.VILLAGES_COLUMN_ID + " INTEGER  PRIMARY KEY, " +
                VillagesPojo.VILLAGES_COLUMN_VILLAGE_NAME + " TEXT) "  );












        IndividualFarmerContactMainActivity.dbCreateStatus=1;

        Log.d("LOG HU","DB CREATE METHOD");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // ((IndividualFarmerContactActivity)getActivity()).getDataSetEntriesMethod();

        Log.d("LOG HU","DB UPGRADE METHOD");



       // activity.getDataSetEntriesMethod();
    }



    public boolean insertDataSetMaster(String dataSetTitle,
             String dataSetDescription, String element1, String element2, String element3, String element4,
             String number1, String number2, String text1, String text2, String date1, String date2, String status, Date createdOn,
             String createdBy,  String clientName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE, dataSetTitle);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_DESCRIPTION,dataSetDescription);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT1,element1);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT2,element2);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT3,element3);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT4,element4);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_NUMBER1,number1);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_NUMBER2,number2);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_TEXT1,text1);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_TEXT2,text2);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_DATE1,date1);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_DATE2,date2);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS,status);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_CREATED_ON,createdOn.toString());
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_CREATED_BY,createdBy);
        contentValues.put(DataSetMasterPojo.DATASETMASTER_COLUMN_CLIENT_NAME, clientName);

        //contentValues.put(PERSON_COLUMN_AGE, age);
        db.insert(DataSetMasterPojo.DATASETMASTER_TABLE_NAME, null, contentValues);


        return true;
    }



    public Cursor getAllDataSetEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + DataSetMasterPojo.DATASETMASTER_TABLE_NAME, null );
        return res;
    }





    public List<String> getCropCategories() {
        SQLiteDatabase db = this.getReadableDatabase();
      /*   Cursor cursor = db.rawQuery( "SELECT * FROM " + DATASETMASTER_TABLE_NAME + " WHERE " +
               DATASETMASTER_COLUMN_DATASET_TITLE +"='CropCategory' ",null ); //AND " +DATASETMASTER_COLUMN_STATUS+" ='Active'
               */



     Cursor cursor = db.rawQuery("select "+ DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE+", "+
             DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS+" , "+DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT1+
             " from "+ DataSetMasterPojo.DATASETMASTER_TABLE_NAME +
             " where "+ DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE+" =? and " +
             DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS+" =? " ,  new String[] {"CropCategory","Active"});


   /*    Cursor c = sqlDatabase.rawQuery("select docid as _id, recipeID from " +
                        TABLE_RECIPE_NAME + " where " + KEY_ownerID + " = ? AND " + KEY_partnerID +
                        " = ? AND  " + KEY_advertiserID + " = ? AND " + KEY_chefID + " = ?",
                new String[] { ownerID, partnerID, advertiserID, chefID });
*/






List<String> cropCategoryList=new ArrayList<String>();
cropCategoryList.add("Select Crop Category*");

//IndividualFarmerContactActivity.test=cursor.getCount();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String categoryName = cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT1));
Log.d("CATEGORYNAME",categoryName);
                //String element1 = cursor.getString(cursor.getColumnIndex(MobileDatabase.DATASETMASTER_COLUMN_ELEMENT1));
                //  String element2 = cursor.getString(cursor.getColumnIndex(MobileDatabase.DATASETMASTER_COLUMN_ELEMENT2));

                //   Toast.makeText(this, "Id from Local DB"+x +" datasettitle "+dataSetTitle, Toast.LENGTH_SHORT).show();
                if(categoryName!=null)
                cropCategoryList.add(categoryName);

                // do what ever you want here
            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();

        Collections.sort(cropCategoryList, new Comparator<String>() { //to sort an list according to one property of it
            @Override
            public int compare(String u1, String u2) {
                if (u1.equals(u2)) // update to make it stable
                    return 0;
                if (u1.equals("Select Crop Category*"))
                    return -1;
                if (u2.equals("Select Crop Category*"))
                    return 1;
                return u1.compareTo(u2);
            }
        });


        return cropCategoryList;
    }


    public List<String> getCropFocus(String cropCategory) {
        SQLiteDatabase db = this.getReadableDatabase();
      /*   Cursor cursor = db.rawQuery( "SELECT * FROM " + DATASETMASTER_TABLE_NAME + " WHERE " +
               DATASETMASTER_COLUMN_DATASET_TITLE +"='CropCategory' ",null ); //AND " +DATASETMASTER_COLUMN_STATUS+" ='Active'
               */



        Cursor cursor = db.rawQuery("select * from "+ DataSetMasterPojo.DATASETMASTER_TABLE_NAME +" where "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE+"=? and " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT1 +"=? and "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS+"=?",  new String[] {"CropCategoryCropFocus",cropCategory,"Active"});






        List<String> cropFocusList=new ArrayList<String>();
        cropFocusList.add("Select Crop Focus*");

       // IndividualFarmerContactActivity.test=cursor.getCount();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String cropFocusName = cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT2));


                //   Toast.makeText(this, "Id from Local DB"+x +" datasettitle "+dataSetTitle, Toast.LENGTH_SHORT).show();
                if(cropFocusName!=null)
                    cropFocusList.add(cropFocusName);

                // do what ever you want here
            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();




        Collections.sort(cropFocusList, new Comparator<String>() { //to sort an list according to one property of it
            @Override
            public int compare(String u1, String u2) {
                if (u1.equals(u2)) // update to make it stable
                    return 0;
                if (u1.equals("Select Crop Focus*"))
                    return -1;
                if (u2.equals("Select Crop Focus*"))
                    return 1;
                return u1.compareTo(u2);
            }
        });

        cropFocusList.add("Others");
        return cropFocusList;

    }





    public void deleteAllEntriesOfDataSetMaster() {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ DataSetMasterPojo.DATASETMASTER_TABLE_NAME);

    }

    /*

    ALL

    String chooseActivity, Date dateOfActivity, Integer numberOfFarmer,
                                         String village, String district, String cropFocus, String cropCategory,
                                         String focusProduct, Integer expenses, String meetingPurpose,byte[] attachFile,
                                         String location, Boolean status, Integer stage, Date createdOn, String createdBy,
                                         String fileName, String observations, String clientName, String cropStage,
                                         String problemCategory, String problemSubCategory, String problemDescription,
                                         String recommendation, String instructionsDose, Boolean expertHelp, String description,  Date nextFvVisitDate
     */
   // dateOfActivity_entered,cropCategory_entered,cropFocus_entered,observations_entered,farmerName_entered,villageName_entered,
  //  farmerMobile_entered,landAcres_entered,expenses_entered
    public boolean insertIFCdata(  String chooseActivity, String dateOfActivity,
                                         String village,  String cropFocus, String cropCategory,
                                  String farmerName,String farmerMobile,String landAcres,
                                         String expenses,String observations,String uploadFlagStatus,
                                   List<RetailerDetailsPojo> retailerDetailsPojoList,List<String> selectedProductsList,
                                   String createdOn,String createdby,String clientName,String faOfficeDistrict)
    {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

       contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CHOOSE_ACTIVITY, chooseActivity);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DATE_OF_ACTIVITY, dateOfActivity);
      //  contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_NUMBER_OF_FARMER,numberOfFarmer );
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_VILLAGE, village);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DISTRICT, faOfficeDistrict);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_FOCUS, cropFocus);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_CATEGORY, cropCategory);
      //  contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_FOCUS_PRODUCT, focusProduct);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_EXPENSES, expenses);
      //  contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_MEETING_PURPOSE, meetingPurpose);
     //   contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ATTACHMENT_FILE, attachFile);
     //   contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_LOCATION, location);
      //  contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_STATUS, status);
      //  contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_STAGE, stage);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CREATED_ON, createdOn);
      contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CREATED_BY, createdby);
    //    contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ATTACHMENT_FILE_NAME, fileName);


        if(observations!=null && !observations.trim().equals(""))
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_OBSERVATIONS, observations);
      contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CLIENT_NAME, clientName);
       // contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_STAGE, cropStage);
     //   contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_PROBLEM_CATEGORY, problemCategory);
       // contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_PROBLEM_SUB_CATEGORY, problemSubCategory);
       // contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_PROBLEM_DESCRIPTION, problemDescription);
     //   contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_RECOMMENDATION, recommendation);
     //   contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_INSTRUCTIONS_DOSE, instructionsDose);
     //   contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_EXPERT_HELP, expertHelp);
       // contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DESCRIPTION, description);
    //    contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_NEXT_FIELD_VISIT_DATE, nextFvVisitDate.toString());


    //    Toast.makeText(IndividualFarmerContactActivity.this.getApplicationContext(), "", Toast.LENGTH_SHORT).show();

        //contentValues.put(PERSON_COLUMN_AGE, age);


        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_UPLOAD_FLAG,uploadFlagStatus);
        db.insert(PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME, null, contentValues);





        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor2 = db2.rawQuery("Select * from " +PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME +
                " order by ROWID DESC limit 1", null );

        Integer id_FM=null;
        if (cursor2 != null && cursor2.moveToFirst()) {
            do {
                 id_FM = cursor2.getInt(cursor2.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ID));

               // Toast.makeText(IndividualFarmerContactActivity.getInstance(), "ID "+id, Toast.LENGTH_SHORT).show();





            } while (cursor2.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor2.close();

        SQLiteDatabase db3 = getWritableDatabase();
        ContentValues contentValues3 = new ContentValues();


        contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_NAME, farmerName);
        contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_LAND,landAcres);
        contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_MOBILE,farmerMobile);
        contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_PROMO_FM_ID,id_FM);

        db3.insert(FarmerDetailsPojo.FARMERDETAILS_TABLE_NAME, null, contentValues3);


      for(int i=0;i<retailerDetailsPojoList.size();i++)
      {
          SQLiteDatabase db4 = getWritableDatabase();
          ContentValues contentValues4 = new ContentValues();

          RetailerDetailsPojo retailerDetailsPojo=retailerDetailsPojoList.get(i);

          String firmName=retailerDetailsPojo.getFirmName();
          String retailerName=retailerDetailsPojo.getRetailerMobile();
          String retailerMobile=retailerDetailsPojo.getRetailerMobile();


          if(firmName!=null && !firmName.trim().equals("")
                  && retailerName!=null && !retailerName.trim().equals("")
          && retailerMobile!=null && !retailerMobile.trim().equals("")
          ) {
              contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_FIRM_NAME, retailerDetailsPojo.getFirmName());
              contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROPRIETOR_NAME, retailerDetailsPojo.getPropName());
              contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_RETAILER_MOBILE, retailerDetailsPojo.getRetailerMobile());
              contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_FM_ID, id_FM);

              db4.insert(RetailerDetailsPojo.RETAILERDETAILS_TABLE_NAME, null, contentValues4);
          }

      }

        for(int i=0;i<selectedProductsList.size();i++)
        {
            SQLiteDatabase db4 = getWritableDatabase();
            ContentValues contentValues4 = new ContentValues();

            String productname=selectedProductsList.get(i);
            contentValues4.put(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PRODUCT_NAME, productname);
            contentValues4.put(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PROMO_FM_ID,id_FM);

            db4.insert(ProductsDetailsPojo.PRODUCTDETAILS_TABLE_NAME, null, contentValues4);
        }



        IndividualFarmerContactMainActivity.dbInsertSTatus=1;
        return true;
    }

    public Cursor getAllPromoEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME , null );
        IndividualFarmerContactMainActivity.dbRetriveSTatus=1;
        return res;
    }

public Cursor getPromoEntriesToBeUploaded()
{
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor res = db.rawQuery("select * from "+PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME +" where "+
            PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_UPLOAD_FLAG+"=?", new String[]{"No"} );
    return res;
}


    public void updateMobileFlagPromoFarmerMeetingEntries(ArrayList<Integer> uploadedIds)
    {

        SQLiteDatabase db=getWritableDatabase();

          for (int i=0;i<uploadedIds.size();i++)
           {
             String id=(uploadedIds.get(i)).toString();
             ContentValues cv = new ContentValues();
             cv.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_UPLOAD_FLAG,"Yes");
             db.update(PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME, cv,PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ID+" = ?", new String[]{id});
           }
    }


    public List<FarmerDetailsPojo> getFarmerDetails(Integer fmId) {
        List<FarmerDetailsPojo> list=new ArrayList<FarmerDetailsPojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +FarmerDetailsPojo.FARMERDETAILS_TABLE_NAME +
                " where "+FarmerDetailsPojo.FARMERDETAILS_COLUMN_PROMO_FM_ID+"=?", new String[]{fmId.toString()} );

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String farmerName = cursor.getString(cursor.getColumnIndex(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_NAME));
                String farmerMobile=cursor.getString(cursor.getColumnIndex(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_MOBILE));
                String farmerLand=cursor.getString(cursor.getColumnIndex(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_LAND));
               //  Toast.makeText(IndividualFarmerContactActivity.getInstance(), "FARMER NAME "+farmerName, Toast.LENGTH_SHORT).show();

                FarmerDetailsPojo farmerDetailsPojo=new FarmerDetailsPojo();
                farmerDetailsPojo.setFarmerName(farmerName);
                farmerDetailsPojo.setFarmerMobile(farmerMobile);
                farmerDetailsPojo.setFarmerLand(farmerLand);

            list.add(farmerDetailsPojo);


            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();

        return list;
    }

    //Retreiving retailerDetails for promoFarmer Metting table by vinod
    public List<RetailerDetailsPojo> getRetailerDetails(Integer fmId) {

            List<RetailerDetailsPojo> list=new ArrayList<RetailerDetailsPojo>();

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * from " +RetailerDetailsPojo.RETAILERDETAILS_TABLE_NAME +
                    " where "+RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_FM_ID+"=?", new String[]{fmId.toString()} );

            Integer id=null;
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    RetailerDetailsPojo retailerDetailsPojo=new RetailerDetailsPojo();
                    String firmName = cursor.getString(cursor.getColumnIndex(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_FIRM_NAME));
                    String propName=  cursor.getString(cursor.getColumnIndex(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROPRIETOR_NAME));
                    String retailerMobile= cursor.getString(cursor.getColumnIndex(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_RETAILER_MOBILE));
                 //   Toast.makeText(IndividualFarmerContactActivity.getInstance(), "FIRM NAME "+farmerName, Toast.LENGTH_SHORT).show();
                    retailerDetailsPojo.setFirmName(firmName);
                    retailerDetailsPojo.setPropName(propName);
                    retailerDetailsPojo.setRetailerMobile(retailerMobile);


              list.add(retailerDetailsPojo);


                } while (cursor.moveToNext());

                // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
            }
            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





            cursor.close();

            return list;
        }

    public List<String> getProductDetails(Integer fmId) {
        List<String> list=new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +ProductsDetailsPojo.PRODUCTDETAILS_TABLE_NAME +
                " where "+ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PROMO_FM_ID+"=?", new String[]{fmId.toString()} );

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(cursor.getColumnIndex(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PRODUCT_NAME));



                list.add(productName);


            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();

        return list;
    }









    public boolean insertFAMasterData(String faFirstName, String faLastName,
                                   String faOfficeTerritory, String clientName, String faValidityFrom, String faValidityTo,
                                   String faDistrict,String headquarter,String faOfficeRegionalOffice,
                                   String faOfficeState,String status)
    {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FaMasterPojo.FAMASTER_COLUMN_FA_FIRSTNAME, faFirstName);
        contentValues.put(FaMasterPojo.FAMASTER_COLUMN_FA_LASTNAME,faLastName);
        contentValues.put(FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_TERRITORY,faOfficeTerritory);
        contentValues.put(FaMasterPojo.FAMASTER_COLUMN_FA_CLIENTNAME,clientName);
        contentValues.put(FaMasterPojo.FAMASTER_COLUMN_FA_VALIDITY_FROM,faValidityFrom);
        contentValues.put(FaMasterPojo.FAMASTER_COLUMN_FA_VALIDITY_TO,faValidityTo);
        contentValues.put(FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_DISTRICT,faDistrict);
        contentValues.put(FaMasterPojo.FAMASTER_COLUMN_FA_HEADQUARTER,headquarter);
        contentValues.put(FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_REGIONAL_OFFICE,faOfficeRegionalOffice);
        contentValues.put(FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_STATE,faOfficeState);
        contentValues.put(FaMasterPojo.FAMASTER_COLUMN_FA_STATUS,status);

        db.insert(FaMasterPojo.FAMASTER_TABLE_NAME, null, contentValues);


        return true;
    }



    public Cursor getFaMasterAllEntries()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+FaMasterPojo.FAMASTER_TABLE_NAME , null );
        IndividualFarmerContactMainActivity.dbRetriveSTatus=1;
        return res;
    }
    public boolean insertUsersData(String userName, String otp,
                                      String password, String status)
    {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UsersPojo.USERS_COLUMN_USERNAME, userName);
        contentValues.put(UsersPojo.USERS_COLUMN_OTP,otp);
        contentValues.put(UsersPojo.USERS_COLUMN_PASSWORD,password);
        contentValues.put(UsersPojo.USERS_COLUMN_STATUS,status);


        db.insert(UsersPojo.USERS_TABLE_NAME, null, contentValues);


        return true;
    }


    public Cursor getUsersAllEntries()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+UsersPojo.USERS_TABLE_NAME , null );
        IndividualFarmerContactMainActivity.dbRetriveSTatus=1;
        return res;
    }


    public boolean insertVillagesData(String villageName)
    {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();




       contentValues.put(VillagesPojo.VILLAGES_COLUMN_VILLAGE_NAME, villageName);


        db.insert(VillagesPojo.VILLAGES_TABLE_NAME, null, contentValues);


        return true;
    }


    public Cursor getVillagesAllEntries()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+VillagesPojo.VILLAGES_TABLE_NAME , null );
        IndividualFarmerContactMainActivity.dbRetriveSTatus=1;
        return res;
    }

    public List<String> getVillageList() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> villageList=new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from "+VillagesPojo.VILLAGES_TABLE_NAME , null );
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String villageName = cursor.getString(cursor.getColumnIndex(VillagesPojo.VILLAGES_COLUMN_VILLAGE_NAME));

                if(villageName.length()>=2) {

                 villageName = villageName.substring(0, 1).toUpperCase() + villageName.substring(1);
                 villageList.add(villageName);

                 }


            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();
        return villageList;
    }



    public List<String> getProductFocusList(String client, String state)
    {
        SQLiteDatabase db = this.getReadableDatabase();
      /*   Cursor cursor = db.rawQuery( "SELECT * FROM " + DATASETMASTER_TABLE_NAME + " WHERE " +
               DATASETMASTER_COLUMN_DATASET_TITLE +"='CropCategory' ",null ); //AND " +DATASETMASTER_COLUMN_STATUS+" ='Active'
               */



        Cursor cursor = db.rawQuery("select * from "+ DataSetMasterPojo.DATASETMASTER_TABLE_NAME +" where "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE+"=? and " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_CLIENT_NAME+"=? and " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT1 +"=? and "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS+"=?",  new String[] {"ClientStateProductValidityFromValidityTo",client,state,"Active"});






        List<String> productFocusList=new ArrayList<String>();
        productFocusList.add("Select Product Focus*");

        // IndividualFarmerContactActivity.test=cursor.getCount();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String validityFrom = cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_DATE1));
                String validityTo=cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_DATE2));
                String productName=cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT2));
              //px22  Toast.makeText(IndividualFarmerContactActivity.getInstance(), "ValidityFrom String "+validityFrom, Toast.LENGTH_SHORT).show();

                Calendar cal=Calendar.getInstance();

                Date currentDate=cal.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date fromDate=sdf.parse(validityFrom);
                    Date toDate=sdf.parse(validityTo);

                    if((currentDate.after(fromDate) || sdf.format(currentDate).equals(sdf.format(fromDate)))
                            && (currentDate.before(toDate) || sdf.format(currentDate).equals(sdf.format(toDate))))
                    {


                        productFocusList.add(productName);


                    }




                } catch (ParseException e) {
                    e.printStackTrace();
                }


                // do what ever you want here
            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();


        Collections.sort(productFocusList, new Comparator<String>() { //to sort an list according to one property of it
            @Override
            public int compare(String u1, String u2) {
                if (u1.equals(u2)) // update to make it stable
                    return 0;
                if (u1.equals("Select Product Focus*"))
                    return -1;
                if (u2.equals("Select Product Focus*"))
                    return 1;
                return u1.compareTo(u2);
            }
        });

        return productFocusList;

    }


    public String getClientName()
    {
        String clientName="";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+FaMasterPojo.FAMASTER_TABLE_NAME, null );
        IndividualFarmerContactMainActivity.dbRetriveSTatus=1;

        // IndividualFarmerContactActivity.test=cursor.getCount();
            if (cursor != null && cursor.moveToFirst()) {
                do
                {
                    clientName=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_CLIENTNAME));
                }
                    while (cursor.moveToNext());

                // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
            }
            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
            cursor.close();



            return clientName;
    }


    public String getStateName() {
        String faOfficeStateName="";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+FaMasterPojo. FAMASTER_TABLE_NAME, null );
        IndividualFarmerContactMainActivity.dbRetriveSTatus=1;

        // IndividualFarmerContactActivity.test=cursor.getCount();
        if (cursor != null && cursor.moveToFirst()) {
            do
            {
                faOfficeStateName=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_STATE));
            }
            while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();



        return faOfficeStateName;
    }


    public String getFaOfficeDistrict()
    {
        String faOfficeDistrict="";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+FaMasterPojo. FAMASTER_TABLE_NAME, null );
        IndividualFarmerContactMainActivity.dbRetriveSTatus=1;

        // IndividualFarmerContactActivity.test=cursor.getCount();
        if (cursor != null && cursor.moveToFirst()) {
            do
            {
                faOfficeDistrict=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_DISTRICT));
            }
            while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();



        return faOfficeDistrict;
    }

    public List<String> getFirmNameList() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from "+ DataSetMasterPojo.DATASETMASTER_TABLE_NAME +" where "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE+"=? and " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS+"=?",  new String[] {"ClientDistrictRetailer","Active"});


        List<String> firmNameList=new ArrayList<String>();

        // IndividualFarmerContactActivity.test=cursor.getCount();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String firmName = cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT2));


                //   Toast.makeText(this, "Id from Local DB"+x +" datasettitle "+dataSetTitle, Toast.LENGTH_SHORT).show();
                if(firmName!=null)
                    firmNameList.add(firmName);

                // do what ever you want here
            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();



        return firmNameList;

    }

    public String getPropName(String firmName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
      /*   Cursor cursor = db.rawQuery( "SELECT * FROM " + DATASETMASTER_TABLE_NAME + " WHERE " +
               DATASETMASTER_COLUMN_DATASET_TITLE +"='CropCategory' ",null ); //AND " +DATASETMASTER_COLUMN_STATUS+" ='Active'
               */

String propName="";

        Cursor cursor = db.rawQuery("select * from "+ DataSetMasterPojo.DATASETMASTER_TABLE_NAME +" where "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE+"=? and " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT2 +"=? and "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS+"=?",  new String[] {"ClientDistrictRetailer",firmName,"Active"});






        // IndividualFarmerContactActivity.test=cursor.getCount();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                 propName = cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT3));









                // do what ever you want here
            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();


        return propName;

    }


    public String getRetailerMobile(String firmName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
      /*   Cursor cursor = db.rawQuery( "SELECT * FROM " + DATASETMASTER_TABLE_NAME + " WHERE " +
               DATASETMASTER_COLUMN_DATASET_TITLE +"='CropCategory' ",null ); //AND " +DATASETMASTER_COLUMN_STATUS+" ='Active'
               */

        String retailerMobile="";

        Cursor cursor = db.rawQuery("select * from "+ DataSetMasterPojo.DATASETMASTER_TABLE_NAME +" where "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE+"=? and " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT2 +"=? and "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS+"=?",  new String[] {"ClientDistrictRetailer",firmName,"Active"});






        // IndividualFarmerContactActivity.test=cursor.getCount();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                retailerMobile = cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT4));









                // do what ever you want here
            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();


        return retailerMobile;

    }

    public List<String> getMeetingPurposeList(String activityName) {
        SQLiteDatabase db = this.getReadableDatabase();
      /*   Cursor cursor = db.rawQuery( "SELECT * FROM " + DATASETMASTER_TABLE_NAME + " WHERE " +
               DATASETMASTER_COLUMN_DATASET_TITLE +"='CropCategory' ",null ); //AND " +DATASETMASTER_COLUMN_STATUS+" ='Active'
               */



        Cursor cursor = db.rawQuery("select * from "+ DataSetMasterPojo.DATASETMASTER_TABLE_NAME +" where "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE+"=? and " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT1 +"=? and "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS+"=?",  new String[] {"ActivityMeetingPurpose",activityName,"Active"});






        List<String> meetingPurposeList=new ArrayList<String>();
        meetingPurposeList.add("Select Meeting Purpose*");

        // IndividualFarmerContactActivity.test=cursor.getCount();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String meetingPurpose = cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT2));


                //   Toast.makeText(this, "Id from Local DB"+x +" datasettitle "+dataSetTitle, Toast.LENGTH_SHORT).show();
                if(meetingPurpose!=null)
                    meetingPurposeList.add(meetingPurpose);

                // do what ever you want here
            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();




        Collections.sort(meetingPurposeList, new Comparator<String>() { //to sort an list according to one property of it
            @Override
            public int compare(String u1, String u2) {
                if (u1.equals(u2)) // update to make it stable
                    return 0;
                if (u1.equals("Select Meeting Purpose*"))
                    return -1;
                if (u2.equals("Select Meeting Purpose*"))
                    return 1;
                return u1.compareTo(u2);
            }
        });

        meetingPurposeList.add("Others");
        return meetingPurposeList;

    }

    public Boolean insertFMdata(String activityName, String dateOfActivity,
                                String noOfFarmers_entered, String expenses_entered,
                                String village_entered, String selectedCropCategory,
                                String selectedCropFocus, String selectedMeetingPurpose,
                                List<String> selectedProductsList, String meetingHighlights,
                                List<FarmerDetailsPojo> farmerDetailsPojoList, List<RetailerDetailsPojo> retailerDetailsPojoList,
                                String uploadFlagStatus, String createdOn_string, String createdby,
                                String clientName, String faOfficeDistrict, List<byte[]> attachmentList)
    {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CHOOSE_ACTIVITY, activityName);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DATE_OF_ACTIVITY, dateOfActivity);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_NUMBER_OF_FARMER,noOfFarmers_entered );
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_EXPENSES, expenses_entered);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_VILLAGE, village_entered);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_CATEGORY, selectedCropCategory);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_FOCUS, selectedCropFocus);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DISTRICT, faOfficeDistrict);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_MEETING_PURPOSE, selectedMeetingPurpose);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DESCRIPTION, meetingHighlights);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CREATED_ON, createdOn_string);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CREATED_BY, createdby);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CLIENT_NAME, clientName);



        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_UPLOAD_FLAG,uploadFlagStatus);
        db.insert(PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME, null, contentValues);





        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor2 = db2.rawQuery("Select * from " +PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME +
                " order by ROWID DESC limit 1", null );

        Integer id_FM=null;
        if (cursor2 != null && cursor2.moveToFirst()) {
            do {
                id_FM = cursor2.getInt(cursor2.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ID));

                // Toast.makeText(IndividualFarmerContactActivity.getInstance(), "ID "+id, Toast.LENGTH_SHORT).show();





            } while (cursor2.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor2.close();

        SQLiteDatabase db3 = getWritableDatabase();
        ContentValues contentValues3 = new ContentValues();

for(int i=0 ; i<farmerDetailsPojoList.size();i++) {
    FarmerDetailsPojo farmerDetailsPojo=farmerDetailsPojoList.get(i);
    contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_NAME, farmerDetailsPojo.getFarmerName());
    contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_LAND, farmerDetailsPojo.getFarmerLand());
    contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_MOBILE, farmerDetailsPojo.getFarmerMobile());
    contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_PROMO_FM_ID, id_FM);

    db3.insert(FarmerDetailsPojo.FARMERDETAILS_TABLE_NAME, null, contentValues3);

}
        for(int i=0;i<retailerDetailsPojoList.size();i++)
        {
            SQLiteDatabase db4 = getWritableDatabase();
            ContentValues contentValues4 = new ContentValues();

            RetailerDetailsPojo retailerDetailsPojo=retailerDetailsPojoList.get(i);

            String firmName=retailerDetailsPojo.getFirmName();
            String retailerName=retailerDetailsPojo.getRetailerMobile();
            String retailerMobile=retailerDetailsPojo.getRetailerMobile();


            if(firmName!=null && !firmName.trim().equals("")
                    && retailerName!=null && !retailerName.trim().equals("")
                    && retailerMobile!=null && !retailerMobile.trim().equals("")
            ) {
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_FIRM_NAME, retailerDetailsPojo.getFirmName());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROPRIETOR_NAME, retailerDetailsPojo.getPropName());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_RETAILER_MOBILE, retailerDetailsPojo.getRetailerMobile());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_FM_ID, id_FM);

                db4.insert(RetailerDetailsPojo.RETAILERDETAILS_TABLE_NAME, null, contentValues4);
            }

        }

        for(int i=0;i<selectedProductsList.size();i++)
        {
            SQLiteDatabase db4 = getWritableDatabase();
            ContentValues contentValues4 = new ContentValues();

            String productname=selectedProductsList.get(i);
            contentValues4.put(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PRODUCT_NAME, productname);
            contentValues4.put(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PROMO_FM_ID,id_FM);

            db4.insert(ProductsDetailsPojo.PRODUCTDETAILS_TABLE_NAME, null, contentValues4);
        }




        for(int j=0; j<attachmentList.size();j++)
        {
            SQLiteDatabase db5 = getWritableDatabase();
            ContentValues contentValues5 = new ContentValues();

            byte[] attachment=attachmentList.get(j);
            contentValues5.put(AttachmentsPojo.ATTACHMENTS_COLUMN_ATTACHMENT_FILE, attachment);
            contentValues5.put(AttachmentsPojo.ATTACHMENTS_COLUMN_PROMO_FM_ID,id_FM);

            db5.insert(AttachmentsPojo.ATTACHMENTS_TABLE_NAME, null, contentValues5);
        }






       // IndividualFarmerContactMainActivity.dbInsertSTatus=1;
        return true;
    }

    public Cursor getAllPromoFMEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME +" where "+
                PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CHOOSE_ACTIVITY +"=? " ,  new String[] {"FM"} );

        return res;
    }





    public Cursor getAllAttachments() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + "IMAGES", null );
        return res;
    }


    public List<byte[]> getAttachments(Integer fmId) {
        List<byte[]> list=new ArrayList<byte[]>();
        byte[] attachment=null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +AttachmentsPojo.ATTACHMENTS_TABLE_NAME +
                " where "+AttachmentsPojo.ATTACHMENTS_COLUMN_PROMO_FM_ID+"=?", new String[]{fmId.toString()} );

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                try {
                     attachment = cursor.getBlob(cursor.getColumnIndex(AttachmentsPojo.ATTACHMENTS_COLUMN_ATTACHMENT_FILE));

                     if(attachment!=null)
                    list.add(attachment);
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }




            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();

        return list;
    }

    public Boolean insertFDdata(String activityName, String dateOfActivity, String noOfFarmers_entered, String expenses_entered, String village_entered,
                             String selectedCropCategory, String selectedCropFocus, String selectedMeetingPurpose,
                             List<String> selectedProductsList, String observations, List<FarmerDetailsPojo> farmerDetailsPojoList,
                             List<RetailerDetailsPojo> retailerDetailsPojoList, String uploadFlagStatus, String createdOn_string, String createdby,
                             String clientName, String faOfficeDistrict, List<byte[]> attachmentList) {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CHOOSE_ACTIVITY, activityName);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DATE_OF_ACTIVITY, dateOfActivity);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_NUMBER_OF_FARMER,noOfFarmers_entered );
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_EXPENSES, expenses_entered);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_VILLAGE, village_entered);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_CATEGORY, selectedCropCategory);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_FOCUS, selectedCropFocus);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DISTRICT, faOfficeDistrict);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_MEETING_PURPOSE, selectedMeetingPurpose);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_OBSERVATIONS, observations);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CREATED_ON, createdOn_string);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CREATED_BY, createdby);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CLIENT_NAME, clientName);



        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_UPLOAD_FLAG,uploadFlagStatus);
        db.insert(PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME, null, contentValues);





        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor2 = db2.rawQuery("Select * from " +PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME +
                " order by ROWID DESC limit 1", null );

        Integer id_FM=null;
        if (cursor2 != null && cursor2.moveToFirst()) {
            do {
                id_FM = cursor2.getInt(cursor2.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ID));

                // Toast.makeText(IndividualFarmerContactActivity.getInstance(), "ID "+id, Toast.LENGTH_SHORT).show();





            } while (cursor2.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor2.close();

        SQLiteDatabase db3 = getWritableDatabase();
        ContentValues contentValues3 = new ContentValues();

        for(int i=0 ; i<farmerDetailsPojoList.size();i++) {
            FarmerDetailsPojo farmerDetailsPojo=farmerDetailsPojoList.get(i);
            contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_NAME, farmerDetailsPojo.getFarmerName());
            contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_LAND, farmerDetailsPojo.getFarmerLand());
            contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_MOBILE, farmerDetailsPojo.getFarmerMobile());
            contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_PROMO_FM_ID, id_FM);

            db3.insert(FarmerDetailsPojo.FARMERDETAILS_TABLE_NAME, null, contentValues3);

        }
        for(int i=0;i<retailerDetailsPojoList.size();i++)
        {
            SQLiteDatabase db4 = getWritableDatabase();
            ContentValues contentValues4 = new ContentValues();

            RetailerDetailsPojo retailerDetailsPojo=retailerDetailsPojoList.get(i);

            String firmName=retailerDetailsPojo.getFirmName();
            String retailerName=retailerDetailsPojo.getRetailerMobile();
            String retailerMobile=retailerDetailsPojo.getRetailerMobile();


            if(firmName!=null && !firmName.trim().equals("")
                    && retailerName!=null && !retailerName.trim().equals("")
                    && retailerMobile!=null && !retailerMobile.trim().equals("")
            ) {
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_FIRM_NAME, retailerDetailsPojo.getFirmName());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROPRIETOR_NAME, retailerDetailsPojo.getPropName());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_RETAILER_MOBILE, retailerDetailsPojo.getRetailerMobile());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_FM_ID, id_FM);

                db4.insert(RetailerDetailsPojo.RETAILERDETAILS_TABLE_NAME, null, contentValues4);
            }

        }

        for(int i=0;i<selectedProductsList.size();i++)
        {
            SQLiteDatabase db4 = getWritableDatabase();
            ContentValues contentValues4 = new ContentValues();

            String productname=selectedProductsList.get(i);
            contentValues4.put(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PRODUCT_NAME, productname);
            contentValues4.put(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PROMO_FM_ID,id_FM);

            db4.insert(ProductsDetailsPojo.PRODUCTDETAILS_TABLE_NAME, null, contentValues4);
        }




        for(int j=0; j<attachmentList.size();j++)
        {
            SQLiteDatabase db5 = getWritableDatabase();
            ContentValues contentValues5 = new ContentValues();

            byte[] attachment=attachmentList.get(j);
            contentValues5.put(AttachmentsPojo.ATTACHMENTS_COLUMN_ATTACHMENT_FILE, attachment);
            contentValues5.put(AttachmentsPojo.ATTACHMENTS_COLUMN_PROMO_FM_ID,id_FM);

            db5.insert(AttachmentsPojo.ATTACHMENTS_TABLE_NAME, null, contentValues5);
        }






        // IndividualFarmerContactMainActivity.dbInsertSTatus=1;
        return true;
    }

    public List<String> getProblemCategories() {
        SQLiteDatabase db = this.getReadableDatabase();




        Cursor cursor = db.rawQuery("select "+ DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE+", "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS+" , "+DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT1+
                " from "+ DataSetMasterPojo.DATASETMASTER_TABLE_NAME +
                " where "+ DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE+" =? and " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS+" =? " ,  new String[] {"ProblemCategory","Active"});


   /*    Cursor c = sqlDatabase.rawQuery("select docid as _id, recipeID from " +
                        TABLE_RECIPE_NAME + " where " + KEY_ownerID + " = ? AND " + KEY_partnerID +
                        " = ? AND  " + KEY_advertiserID + " = ? AND " + KEY_chefID + " = ?",
                new String[] { ownerID, partnerID, advertiserID, chefID });
*/






        List<String> problemCategoryList=new ArrayList<String>();
        problemCategoryList.add("Select Problem Category*");

//IndividualFarmerContactActivity.test=cursor.getCount();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String problemCategoryName = cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT1));
                Log.d("PROBLEMCATEGORYNAME",problemCategoryName);
                //String element1 = cursor.getString(cursor.getColumnIndex(MobileDatabase.DATASETMASTER_COLUMN_ELEMENT1));
                //  String element2 = cursor.getString(cursor.getColumnIndex(MobileDatabase.DATASETMASTER_COLUMN_ELEMENT2));

                //   Toast.makeText(this, "Id from Local DB"+x +" datasettitle "+dataSetTitle, Toast.LENGTH_SHORT).show();
                if(problemCategoryName!=null)
                    problemCategoryList.add(problemCategoryName);

                // do what ever you want here
            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();

        Collections.sort(problemCategoryList, new Comparator<String>() { //to sort an list according to one property of it
            @Override
            public int compare(String u1, String u2) {
                if (u1.equals(u2)) // update to make it stable
                    return 0;
                if (u1.equals("Select Problem Category*"))
                    return -1;
                if (u2.equals("Select Problem Category*"))
                    return 1;
                return u1.compareTo(u2);
            }
        });


        return problemCategoryList;
    }

    public List<String> getProblemSubCategoryList(String selectedProblemCategory) {
        SQLiteDatabase db = this.getReadableDatabase();
      /*   Cursor cursor = db.rawQuery( "SELECT * FROM " + DATASETMASTER_TABLE_NAME + " WHERE " +
               DATASETMASTER_COLUMN_DATASET_TITLE +"='CropCategory' ",null ); //AND " +DATASETMASTER_COLUMN_STATUS+" ='Active'
               */



        Cursor cursor = db.rawQuery("select * from "+ DataSetMasterPojo.DATASETMASTER_TABLE_NAME +" where "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE+"=? and " +
                DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT1 +"=? and "+
                DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS+"=?",  new String[] {"ProblemCategoryProblemSubCategory",selectedProblemCategory,"Active"});






        List<String> problemSubCategoryList=new ArrayList<String>();
        problemSubCategoryList.add("Select Problem Sub Category*");

        // IndividualFarmerContactActivity.test=cursor.getCount();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String problemSubCategory = cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_ELEMENT2));


                //   Toast.makeText(this, "Id from Local DB"+x +" datasettitle "+dataSetTitle, Toast.LENGTH_SHORT).show();
                if(problemSubCategory!=null)
                    problemSubCategoryList.add(problemSubCategory);

                // do what ever you want here
            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();




        Collections.sort(problemSubCategoryList, new Comparator<String>() { //to sort an list according to one property of it
            @Override
            public int compare(String u1, String u2) {
                if (u1.equals(u2)) // update to make it stable
                    return 0;
                if (u1.equals("Select Problem Sub Category*"))
                    return -1;
                if (u2.equals("Select Problem Sub Category*"))
                    return 1;
                return u1.compareTo(u2);
            }
        });

        problemSubCategoryList.add("Others");
        return problemSubCategoryList;

    }


    public boolean insertDVdata(String activityName, String dateOfActivity_entered, String farmerName_entered,
                             String farmerMobile_entered, String village_entered, String selectedCropCategory,
                             String selectedCropFocus, String selectedProblemCategory,
                             String selectedProblemSubCategory, String selectedMeetingPurpose,
                             String nextVisitDate, String cropStage_entered, String expense_entered,
                             String problemDescription_entered, String recommendation_entered,
                             String instructionsDose_entered, String description_entered, List<byte[]> attachmentList,
                             List<RetailerDetailsPojo> retailerDetailsPojoList, String uploadFlagStatus,
                             String createdOn_string, String createdby, String clientName, String faOfficeDistrict) {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CHOOSE_ACTIVITY, activityName);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DATE_OF_ACTIVITY, dateOfActivity_entered);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_VILLAGE, village_entered);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_CATEGORY, selectedCropCategory);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_FOCUS, selectedCropFocus);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_PROBLEM_CATEGORY, selectedProblemCategory);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_PROBLEM_SUB_CATEGORY, selectedProblemSubCategory);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_MEETING_PURPOSE,selectedMeetingPurpose);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_STAGE, cropStage_entered);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_EXPENSES, expense_entered);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_PROBLEM_DESCRIPTION, problemDescription_entered);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_RECOMMENDATION, recommendation_entered);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_INSTRUCTIONS_DOSE, instructionsDose_entered);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DESCRIPTION, description_entered);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_NEXT_FIELD_VISIT_DATE, nextVisitDate);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DISTRICT, faOfficeDistrict);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CREATED_ON, createdOn_string);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CREATED_BY, createdby);
        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CLIENT_NAME, clientName);



        contentValues.put(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_UPLOAD_FLAG,uploadFlagStatus);
        db.insert(PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME, null, contentValues);





        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor2 = db2.rawQuery("Select * from " +PromoFarmerMeetingPojo.PROMOFARMERMEETING_TABLE_NAME +
                " order by ROWID DESC limit 1", null );

       // db2.close();

        Integer id_FM=null;
        if (cursor2 != null && cursor2.moveToFirst()) {
            do {
                id_FM = cursor2.getInt(cursor2.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ID));

                // Toast.makeText(IndividualFarmerContactActivity.getInstance(), "ID "+id, Toast.LENGTH_SHORT).show();





            } while (cursor2.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor2.close();

        SQLiteDatabase db3 = getWritableDatabase();
        ContentValues contentValues3 = new ContentValues();



            contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_NAME, farmerName_entered);
            contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_MOBILE, farmerMobile_entered);
            contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_PROMO_FM_ID, id_FM);

            db3.insert(FarmerDetailsPojo.FARMERDETAILS_TABLE_NAME, null, contentValues3);


        for(int i=0;i<retailerDetailsPojoList.size();i++)
        {
            SQLiteDatabase db4 = getWritableDatabase();
            ContentValues contentValues4 = new ContentValues();

            RetailerDetailsPojo retailerDetailsPojo=retailerDetailsPojoList.get(i);

            String firmName=retailerDetailsPojo.getFirmName();
            String retailerName=retailerDetailsPojo.getRetailerMobile();
            String retailerMobile=retailerDetailsPojo.getRetailerMobile();


            if(firmName!=null && !firmName.trim().equals("")
                    && retailerName!=null && !retailerName.trim().equals("")
                    && retailerMobile!=null && !retailerMobile.trim().equals("")
            ) {
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_FIRM_NAME, retailerDetailsPojo.getFirmName());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROPRIETOR_NAME, retailerDetailsPojo.getPropName());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_RETAILER_MOBILE, retailerDetailsPojo.getRetailerMobile());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_FM_ID, id_FM);

                db4.insert(RetailerDetailsPojo.RETAILERDETAILS_TABLE_NAME, null, contentValues4);
            }

        }






        for(int j=0; j<attachmentList.size();j++)
        {
            SQLiteDatabase db5 = getWritableDatabase();
            ContentValues contentValues5 = new ContentValues();

            byte[] attachment=attachmentList.get(j);
            contentValues5.put(AttachmentsPojo.ATTACHMENTS_COLUMN_ATTACHMENT_FILE, attachment);
            contentValues5.put(AttachmentsPojo.ATTACHMENTS_COLUMN_PROMO_FM_ID,id_FM);

            db5.insert(AttachmentsPojo.ATTACHMENTS_TABLE_NAME, null, contentValues5);
        }






        // IndividualFarmerContactMainActivity.dbInsertSTatus=1;
        return true;
    }

    public static final String MANDICAMPAIGN_COLUMN_DATE_OF_ACTIVITY = "dateofactivity";
    public static final String MANDICAMPAIGN_COLUMN_MANDI_NAME = "mandiname";
    public static final String MANDICAMPAIGN_COLUMN_DISTRICT = "district";
    public static final String MANDICAMPAIGN_COLUMN_CROP_CATEGORY = "cropcategory";
    public static final String MANDICAMPAIGN_COLUMN_CROP_FOCUS = "cropfocus";
    public static final String MANDICAMPAIGN_COLUMN_EXPENSES = "expenses";
    public static final String MANDICAMPAIGN_COLUMN_CAMPAIGN_PURPOSE = "campaignpurpose";
    public static final String MANDICAMPAIGN_COLUMN_ACTIVITY_SUMMARY = "activitysummary";
    public static final String MANDICAMPAIGN_COLUMN_CREATED_ON = "createdon";
    public static final String MANDICAMPAIGN_COLUMN_CREATED_BY = "createdby";
    public static final String MANDICAMPAIGN_COLUMN_CLIENT_NAME = "clientname";
    public static final String MANDICAMPAIGN_COLUMN_UPLOAD_FLAG = "uploadflag";

    public boolean insertMCdata(String dateOfActivity,
                                   String mandiName,  String faOfficeDistrict, String cropCategory,
                                   String cropFocus, String expenses, String  campaignpurpose, String activitySummary,
                                   String createdOn,String createdby,String clientName, String uploadFlag,
                                List<RetailerDetailsPojo> retailerDetailsPojoList, List<String> selectedProductsList,
                                List<byte[]> attachmentList)
    {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_DATE_OF_ACTIVITY, dateOfActivity);
        contentValues.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_MANDI_NAME, mandiName);
        contentValues.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_DISTRICT, faOfficeDistrict);
        contentValues.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CROP_CATEGORY, cropCategory);
        contentValues.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CROP_FOCUS, cropFocus);
        contentValues.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_EXPENSES, expenses);
        contentValues.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CAMPAIGN_PURPOSE, campaignpurpose);
        contentValues.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_ACTIVITY_SUMMARY, activitySummary);
        contentValues.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CREATED_ON, createdOn);
        contentValues.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CREATED_BY, createdby);
        contentValues.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CLIENT_NAME, clientName);
        contentValues.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_UPLOAD_FLAG,uploadFlag);

        db.insert(MandiCampaignPojo.MANDICAMPAIGN_TABLE_NAME, null, contentValues);




        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor2 = db2.rawQuery("Select * from " +MandiCampaignPojo.MANDICAMPAIGN_TABLE_NAME +
                " order by ROWID DESC limit 1", null );


        Integer id_MC=null;
        if (cursor2 != null && cursor2.moveToFirst()) {
            do {
                id_MC = cursor2.getInt(cursor2.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_ID));

                // Toast.makeText(IndividualFarmerContactActivity.getInstance(), "ID "+id, Toast.LENGTH_SHORT).show();





            } while (cursor2.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor2.close();

       // SQLiteDatabase db3 = getWritableDatabase();
        //ContentValues contentValues3 = new ContentValues();

        for(int i=0;i<retailerDetailsPojoList.size();i++)
        {
            SQLiteDatabase db4 = getWritableDatabase();
            ContentValues contentValues4 = new ContentValues();

            RetailerDetailsPojo retailerDetailsPojo=retailerDetailsPojoList.get(i);

            String firmName=retailerDetailsPojo.getFirmName();
            String retailerName=retailerDetailsPojo.getRetailerMobile();
            String retailerMobile=retailerDetailsPojo.getRetailerMobile();


            if(firmName!=null && !firmName.trim().equals("")
                    && retailerName!=null && !retailerName.trim().equals("")
                    && retailerMobile!=null && !retailerMobile.trim().equals("")
            ) {
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_FIRM_NAME, retailerDetailsPojo.getFirmName());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROPRIETOR_NAME, retailerDetailsPojo.getPropName());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_RETAILER_MOBILE, retailerDetailsPojo.getRetailerMobile());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_MC_ID, id_MC);

                db4.insert(RetailerDetailsPojo.RETAILERDETAILS_TABLE_NAME, null, contentValues4);
            }

        }

        for(int i=0;i<selectedProductsList.size();i++)
        {
            SQLiteDatabase db4 = getWritableDatabase();
            ContentValues contentValues4 = new ContentValues();

            String productname=selectedProductsList.get(i);
            contentValues4.put(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PRODUCT_NAME, productname);
            contentValues4.put(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PROMO_MC_ID,id_MC);

            db4.insert(ProductsDetailsPojo.PRODUCTDETAILS_TABLE_NAME, null, contentValues4);
        }




        for(int j=0; j<attachmentList.size();j++)
        {
            SQLiteDatabase db5 = getWritableDatabase();
            ContentValues contentValues5 = new ContentValues();

            byte[] attachment=attachmentList.get(j);
            contentValues5.put(AttachmentsPojo.ATTACHMENTS_COLUMN_ATTACHMENT_FILE, attachment);
            contentValues5.put(AttachmentsPojo.ATTACHMENTS_COLUMN_PROMO_MC_ID,id_MC);

            db5.insert(AttachmentsPojo.ATTACHMENTS_TABLE_NAME, null, contentValues5);
        }






        // IndividualFarmerContactMainActivity.dbInsertSTatus=1;
        return true;
    }


    public Cursor getMandiCampaignEntriesToBeUploaded() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+MandiCampaignPojo.MANDICAMPAIGN_TABLE_NAME +" where "+
                MandiCampaignPojo.MANDICAMPAIGN_COLUMN_UPLOAD_FLAG +"=?", new String[]{"No"} );
        return res;
    }

    public List<RetailerDetailsPojo> getRetailerDetailsForMandiCampaignEntries(Integer mc_id) {

        List<RetailerDetailsPojo> list=new ArrayList<RetailerDetailsPojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +RetailerDetailsPojo.RETAILERDETAILS_TABLE_NAME +
                " where "+RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_MC_ID+"=?", new String[]{mc_id.toString()} );

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                RetailerDetailsPojo retailerDetailsPojo=new RetailerDetailsPojo();
                String firmName = cursor.getString(cursor.getColumnIndex(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_FIRM_NAME));
                String propName=  cursor.getString(cursor.getColumnIndex(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROPRIETOR_NAME));
                String retailerMobile= cursor.getString(cursor.getColumnIndex(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_RETAILER_MOBILE));
                //   Toast.makeText(IndividualFarmerContactActivity.getInstance(), "FIRM NAME "+farmerName, Toast.LENGTH_SHORT).show();
                retailerDetailsPojo.setFirmName(firmName);
                retailerDetailsPojo.setPropName(propName);
                retailerDetailsPojo.setRetailerMobile(retailerMobile);


                list.add(retailerDetailsPojo);


            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();

        return list;
    }

    public List<String> getProductDetailsForMandiCampaignEntries(Integer mc_id) {
        List<String> list=new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +ProductsDetailsPojo.PRODUCTDETAILS_TABLE_NAME +
                " where "+ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PROMO_MC_ID+"=?", new String[]{mc_id.toString()} );

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(cursor.getColumnIndex(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PRODUCT_NAME));



                list.add(productName);


            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();
        return list;
    }

    public List<byte[]> getAttachmentsForMandiCampaignEntries(Integer mc_id) {
        List<byte[]> list=new ArrayList<byte[]>();
        byte[] attachment=null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +AttachmentsPojo.ATTACHMENTS_TABLE_NAME +
                " where "+AttachmentsPojo.ATTACHMENTS_COLUMN_PROMO_MC_ID+"=?", new String[]{mc_id.toString()} );

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                try {
                    attachment = cursor.getBlob(cursor.getColumnIndex(AttachmentsPojo.ATTACHMENTS_COLUMN_ATTACHMENT_FILE));

                    if(attachment!=null)
                        list.add(attachment);
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }




            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();

        return list;
    }

    public void updateMobileFlagForMandiCampaignEntries(ArrayList<Integer> uploadedIds) {

        SQLiteDatabase db=getWritableDatabase();

        for (int i=0;i<uploadedIds.size();i++)
        {
            String id=(uploadedIds.get(i)).toString();
            ContentValues cv = new ContentValues();
            cv.put(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_UPLOAD_FLAG,"Yes");
            db.update(MandiCampaignPojo.MANDICAMPAIGN_TABLE_NAME, cv,MandiCampaignPojo.MANDICAMPAIGN_COLUMN_ID+" = ?", new String[]{id});
        }
    }

    public void insertDemoL3FarmerDetailsdata(String demoL3SerialId, String dateOfActivity_entered,
                                              String farmerName_entered, String faOfficeDistrict,
                                              String farmerMobile_entered, String farmerLandAcres_entered,
                                              String village_entered, String createdOn_string, String createdby,
                                              String clientName, String uploadFlagStatus,
                                              List<RetailerDetailsPojo> retailerDetailsPojoList,
                                              List<byte[]> attachmentList,
                                              String modifyDate_string,
                                              Integer stage) {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID, demoL3SerialId);
        contentValues.put(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_ACTIVITY, dateOfActivity_entered);
        contentValues.put(DemoL3Pojo.DEMOL3_COLUMN_DISTRICT, faOfficeDistrict);
        contentValues.put(DemoL3Pojo.DEMOL3_COLUMN_VILLAGE, village_entered);
        contentValues.put(DemoL3Pojo.DEMOL3_COLUMN_CREATED_ON, createdOn_string);
        contentValues.put(DemoL3Pojo.DEMOL3_COLUMN_MODIFY_DATE,modifyDate_string);
        contentValues.put(DemoL3Pojo.DEMOL3_COLUMN_CREATED_BY, createdby);
        contentValues.put(DemoL3Pojo.DEMOL3_COLUMN_CLIENT_NAME, clientName);
        contentValues.put(DemoL3Pojo.DEMOL3_COLUMN_UPLOAD_FLAG, uploadFlagStatus);
        contentValues.put(DemoL3Pojo.DEMOL3_COLUMN_STAGE, stage);






        db.insert(DemoL3Pojo.DEMOL3_TABLE_NAME, null, contentValues);




        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor2 = db2.rawQuery("Select * from " +DemoL3Pojo.DEMOL3_TABLE_NAME +
                " order by ROWID DESC limit 1", null );


        Integer id_demoL3_SerialId = null;
        if (cursor2 != null && cursor2.moveToFirst()) {
            do {
                id_demoL3_SerialId = cursor2.getInt(cursor2.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID));

                // Toast.makeText(IndividualFarmerContactActivity.getInstance(), "ID "+id, Toast.LENGTH_SHORT).show();





            } while (cursor2.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor2.close();

        SQLiteDatabase db3 = getWritableDatabase();
        ContentValues contentValues3 = new ContentValues();


        contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_NAME, farmerName_entered);
        contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_LAND,farmerLandAcres_entered);
        contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_MOBILE,farmerMobile_entered);
        contentValues3.put(FarmerDetailsPojo.FARMERDETAILS_COLUMN_PROMO_DEMOL3Serial_ID,id_demoL3_SerialId);

        db3.insert(FarmerDetailsPojo.FARMERDETAILS_TABLE_NAME, null, contentValues3);


        for(int i=0;i<retailerDetailsPojoList.size();i++)
        {
            SQLiteDatabase db4 = getWritableDatabase();
            ContentValues contentValues4 = new ContentValues();

            RetailerDetailsPojo retailerDetailsPojo=retailerDetailsPojoList.get(i);

            String firmName=retailerDetailsPojo.getFirmName();
            String retailerName=retailerDetailsPojo.getRetailerMobile();
            String retailerMobile=retailerDetailsPojo.getRetailerMobile();


            if(firmName!=null && !firmName.trim().equals("")
                    && retailerName!=null && !retailerName.trim().equals("")
                    && retailerMobile!=null && !retailerMobile.trim().equals("")
            ) {
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_FIRM_NAME, retailerDetailsPojo.getFirmName());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROPRIETOR_NAME, retailerDetailsPojo.getPropName());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_RETAILER_MOBILE, retailerDetailsPojo.getRetailerMobile());
                contentValues4.put(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_DEMOL3Serial_ID, id_demoL3_SerialId);

                db4.insert(RetailerDetailsPojo.RETAILERDETAILS_TABLE_NAME, null, contentValues4);
            }

        }





        for(int j=0; j<attachmentList.size();j++)
        {
            SQLiteDatabase db5 = getWritableDatabase();
            ContentValues contentValues5 = new ContentValues();

            byte[] attachment=attachmentList.get(j);
            contentValues5.put(AttachmentsPojo.ATTACHMENTS_COLUMN_ATTACHMENT_FILE, attachment);
            contentValues5.put(AttachmentsPojo.ATTACHMENTS_COLUMN_PROMO_DEMOL3Serial_ID,id_demoL3_SerialId);

            db5.insert(AttachmentsPojo.ATTACHMENTS_TABLE_NAME, null, contentValues5);
        }

    }



    public Cursor getAllEntriesFromDemoL3() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + DemoL3Pojo.DEMOL3_TABLE_NAME, null );
        return res;
    }

    public String getStage(Integer demoSerialId) {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.rawQuery("Select * from " +DemoL3Pojo.DEMOL3_TABLE_NAME +
                " where "+DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID+"=?", new String[]{demoSerialId.toString()} );


        String stage = "" ;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                 stage = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_STAGE));

               } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();

        return stage;
    }

    public void insertDemoL3Protocoldata_Update(String dateOfProtocol_entered, String cropCategory_entered,
                                                     String cropFocus_entered, String meetingPurpose_entered,
                                                     List<String> selectedProductList, Integer demoL3SerialId,
                                                     String uploadFlagStatus, String modifyDate_string,
                                                     Integer stage) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
      String sql=   "UPDATE " +DemoL3Pojo.DEMOL3_TABLE_NAME +" SET " +DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_PROTOCOL +" = " +"\'"+dateOfProtocol_entered+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_CROP_CATEGORY +" = " +"\'"+cropCategory_entered+"\'" +" , "+
              DemoL3Pojo.DEMOL3_COLUMN_CROP_FOCUS+ " = " +"\'"+cropFocus_entered +"\'"+" , "+
              DemoL3Pojo.DEMOL3_COLUMN_DEMO_PURPOSE +" = "+"\'"+meetingPurpose_entered +"\'"+" , "+
              DemoL3Pojo.DEMOL3_COLUMN_UPLOAD_FLAG +" = "+"\'"+uploadFlagStatus+"\'" +" , "+
              DemoL3Pojo.DEMOL3_COLUMN_MODIFY_DATE +" = "+"\'"+modifyDate_string+"\'" +" , "+
              DemoL3Pojo.DEMOL3_COLUMN_STAGE +" = "+"\'"+stage+"\'"
              + " WHERE " +DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID +" = " +demoL3SerialId.toString();


      db.execSQL(sql);

        for(int i=0;i<selectedProductList.size();i++)
        {
            SQLiteDatabase db4 = getWritableDatabase();
            ContentValues contentValues4 = new ContentValues();

            String productname=selectedProductList.get(i);
            contentValues4.put(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PRODUCT_NAME, productname);
            contentValues4.put(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PROMO_DEMOL3Serial_ID,demoL3SerialId);

            db4.insert(ProductsDetailsPojo.PRODUCTDETAILS_TABLE_NAME, null, contentValues4);
        }
        // myDB.update(TableName, "(Field1, Field2, Field3)" + " VALUES ('Bob', 19, 'Male')", "where _id = 1", null);

    }

    public void insertDemoL3Executiondata_Update(String dateOfExecution_entered, String dose_entered, String demoArea_entered,
                                                 String modifyDate_string, Integer stage, Integer demoL3SerialId,
                                                 String uploadFlagStatus, List<byte[]> attachmentList) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String sql=   "UPDATE " +DemoL3Pojo.DEMOL3_TABLE_NAME +" SET " +DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_EXECUTION +" = " +"\'"+dateOfExecution_entered+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_DOSE +" = " +"\'"+dose_entered+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_ACRES+ " = " +"\'"+demoArea_entered +"\'"+" , "+
                DemoL3Pojo.DEMOL3_COLUMN_UPLOAD_FLAG +" = "+"\'"+uploadFlagStatus+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_MODIFY_DATE +" = "+"\'"+modifyDate_string+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_STAGE +" = "+"\'"+stage+"\'"
                + " WHERE " +DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID +" = " +demoL3SerialId.toString();


        db.execSQL(sql);

        for(int j=0; j<attachmentList.size();j++)
        {
            SQLiteDatabase db5 = getWritableDatabase();
            ContentValues contentValues5 = new ContentValues();

            byte[] attachment=attachmentList.get(j);
            contentValues5.put(AttachmentsPojo.ATTACHMENTS_COLUMN_ATTACHMENT_FILE, attachment);
            contentValues5.put(AttachmentsPojo.ATTACHMENTS_COLUMN_PROMO_DEMOL3Serial_ID,demoL3SerialId);

            db5.insert(AttachmentsPojo.ATTACHMENTS_TABLE_NAME, null, contentValues5);
        }


    }

    public void insertDemoL3Interimdata_Update(String dateOfInterim_entered, String interimResult_entered,
                                               String modifyDate_string, Integer stage,
                                               String uploadFlagStatus, Integer demoL3SerialId) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String sql=   "UPDATE " +DemoL3Pojo.DEMOL3_TABLE_NAME +" SET " +DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_INTERIM +" = " +"\'"+dateOfInterim_entered+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_INTERIM +" = " +"\'"+interimResult_entered+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_UPLOAD_FLAG +" = "+"\'"+uploadFlagStatus+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_MODIFY_DATE +" = "+"\'"+modifyDate_string+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_STAGE +" = "+"\'"+stage+"\'"
                + " WHERE " +DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID +" = " +demoL3SerialId.toString();


        db.execSQL(sql);

    }

    public void insertDemoL3Yielddata_Update(String dateOfYield_entered, String yieldResult_entered,
                                             String expense_entered, Integer demoL3SerialId, String modifyDate_string,
                                             String uploadFlagStatus, Integer stage) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String sql=   "UPDATE " +DemoL3Pojo.DEMOL3_TABLE_NAME +" SET " +DemoL3Pojo.DEMOL3_COLUMN_DATEOFYIELD +" = " +"\'"+dateOfYield_entered+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_YIELD +" = " +"\'"+yieldResult_entered+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_EXPENSES +" = " +"\'"+expense_entered+"\'" +" , "+

                DemoL3Pojo.DEMOL3_COLUMN_UPLOAD_FLAG +" = "+"\'"+uploadFlagStatus+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_MODIFY_DATE +" = "+"\'"+modifyDate_string+"\'" +" , "+
                DemoL3Pojo.DEMOL3_COLUMN_STAGE +" = "+"\'"+stage+"\'"+
                 " WHERE " +DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID +" = " +demoL3SerialId.toString();


        db.execSQL(sql);






    }

    public Cursor getDemoL3DataFromID(Integer demoL3SerialId) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +DemoL3Pojo.DEMOL3_TABLE_NAME +
                " where "+DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID+"=?", new String[]{demoL3SerialId.toString()} );

       return cursor;
    }

    public FarmerDetailsPojo getFarmerDetailsForDemoL3(String demoL3SerialId) {
        SQLiteDatabase db = this.getReadableDatabase();
        FarmerDetailsPojo farmerDetailsPojo = new FarmerDetailsPojo();
        Cursor cursor = db.rawQuery("Select * from " +FarmerDetailsPojo.FARMERDETAILS_TABLE_NAME +
                " where "+FarmerDetailsPojo.FARMERDETAILS_COLUMN_PROMO_DEMOL3Serial_ID+"=?", new String[]{demoL3SerialId} );
        if (cursor != null && cursor.moveToFirst()) {
            do {
                try {
                    String farmerName = cursor.getString(cursor.getColumnIndex(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_NAME));
                    String farmerMobile = cursor.getString(cursor.getColumnIndex(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_MOBILE));
                    String farmerLand = cursor.getString(cursor.getColumnIndex(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_LAND));


                    farmerDetailsPojo.setFarmerName(farmerName);
                    farmerDetailsPojo.setFarmerMobile(farmerMobile);
                    farmerDetailsPojo.setFarmerLand(farmerLand);


                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }




            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();

return farmerDetailsPojo;
    }

    public List<RetailerDetailsPojo> getRetailerDetailsListForDemoL3(String demoL3SerialId) {
        List<RetailerDetailsPojo> list=new ArrayList<RetailerDetailsPojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +RetailerDetailsPojo.RETAILERDETAILS_TABLE_NAME +
                " where "+RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_DEMOL3Serial_ID+"=?", new String[]{demoL3SerialId} );

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                RetailerDetailsPojo retailerDetailsPojo=new RetailerDetailsPojo();
                String firmName = cursor.getString(cursor.getColumnIndex(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_FIRM_NAME));
                String propName=  cursor.getString(cursor.getColumnIndex(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROPRIETOR_NAME));
                String retailerMobile= cursor.getString(cursor.getColumnIndex(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_RETAILER_MOBILE));
                //   Toast.makeText(IndividualFarmerContactActivity.getInstance(), "FIRM NAME "+farmerName, Toast.LENGTH_SHORT).show();
                retailerDetailsPojo.setFirmName(firmName);
                retailerDetailsPojo.setPropName(propName);
                retailerDetailsPojo.setRetailerMobile(retailerMobile);


                list.add(retailerDetailsPojo);


            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();

        return list;


    }

    public List<String> getSelectedProductList(Integer demoL3SerialId) {
        List<String> list=new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +ProductsDetailsPojo.PRODUCTDETAILS_TABLE_NAME +
                " where "+ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PROMO_DEMOL3Serial_ID+"=?", new String[]{demoL3SerialId.toString()} );

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(cursor.getColumnIndex(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PRODUCT_NAME));


                list.add(productName);


            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();
        return list;


    }

    public List<DemoL3ListItem> getAll_Incompleted_DemoL3Data() { // here we used not equal to stage 6 i.e incompleted demoL3 entries willl be retriieved ..we used !?= here ! for not
        SQLiteDatabase db = this.getReadableDatabase();
        List<DemoL3ListItem> list =  new ArrayList<DemoL3ListItem>();
        String farmerName ="";
    /*    Cursor cursor = db.rawQuery("Select * from " +DemoL3Pojo.DEMOL3_TABLE_NAME +
                " where "+DemoL3Pojo.DEMOL3_COLUMN_STAGE+"!=? ", new String[]{CommonConstants.CLOSE.toString()} ); */

    String notequal ="!='6'";

       Cursor cursor=   db.rawQuery("Select "   +DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID   +" ,"
               +DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_ACTIVITY +" ,"
               +DemoL3Pojo.DEMOL3_COLUMN_MODIFY_DATE +" ,"
               +DemoL3Pojo.DEMOL3_COLUMN_PERMANENT_DEMOL3_SERIAL_ID +" ,"
               +DemoL3Pojo.DEMOL3_COLUMN_VILLAGE


               +" from "+DemoL3Pojo.DEMOL3_TABLE_NAME + " where "+DemoL3Pojo.DEMOL3_COLUMN_STAGE +"!='6'"   +" order by "+DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_ACTIVITY +" DESC ", null);


    //    String dateOfActivity = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_ACTIVITY));
      //  String lastUpdatedOn = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_MODIFY_DATE));
        //String demoL3TempId = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID));
        //String permanentId = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_PERMANENT_DEMOL3_SERIAL_ID));
        //farmer Name is taken above
        //String villageName = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_VILLAGE));

//&& !cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_STAGE)).equals(CommonConstants.CLOSE.toString())



                if (cursor != null && cursor.moveToFirst()  ) {
//                    Toast.makeText(DemoL3_InProgressActivity.getInstance(), "STAGE1 "+cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_STAGE)) ,
  //                  Toast.LENGTH_SHORT).show();

                    do {
    //                    Toast.makeText(DemoL3_InProgressActivity.getInstance(), "STAGE "+cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_STAGE)) ,
      //                          Toast.LENGTH_SHORT).show();

                     //   if (!cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_STAGE)).equals(CommonConstants.CLOSE.toString())) {
                            String demoL3SerialId = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID));

                            //STARTS: different cursor for retrieving farmer details.. frmer retrieved will always be one in demoL3 case
                            Cursor farmerCursor = db.rawQuery("Select " + FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_NAME + "  from " + FarmerDetailsPojo.FARMERDETAILS_TABLE_NAME +
                                    " where " + FarmerDetailsPojo.FARMERDETAILS_COLUMN_PROMO_DEMOL3Serial_ID + "=?", new String[]{demoL3SerialId});


                            if (farmerCursor != null && farmerCursor.moveToFirst()) {
                                do {
                                    try {
                                        farmerName = farmerCursor.getString(farmerCursor.getColumnIndex(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_NAME));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                } while (farmerCursor.moveToNext());

                            }

                            farmerCursor.close();

                            //ENDS : different cursor for retrieving farmer details.. frmer retrieved will always be one in demoL3 case


                            String dateOfActivity = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_ACTIVITY));
                            String lastUpdatedOn = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_MODIFY_DATE));
                            String demoL3TempId = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID));
                            String permanentId = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_PERMANENT_DEMOL3_SERIAL_ID));
                            //farmer Name is taken above
                            String villageName = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_VILLAGE));


                            DemoL3ListItem demoL3ListItem = new DemoL3ListItem(dateOfActivity, lastUpdatedOn, demoL3TempId, permanentId, farmerName, villageName);

                            list.add(demoL3ListItem);
                            // }

                        //  }

                        }


                        while (cursor.moveToNext());

                }
            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();

        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();



        //db.close();
        return list;
    }

    public Cursor getDemoL3Data_EntriesToBeUploaded() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+ DemoL3Pojo.DEMOL3_TABLE_NAME +" where "+
                DemoL3Pojo.DEMOL3_COLUMN_UPLOAD_FLAG +"=?", new String[]{"No"} );
        return res;
    }


    public List<RetailerDetailsPojo> getRetailerDetailsForDEMOL3Entries(String demol3SerialId) {

        List<RetailerDetailsPojo> list=new ArrayList<RetailerDetailsPojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +RetailerDetailsPojo.RETAILERDETAILS_TABLE_NAME +
                " where "+RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROMO_DEMOL3Serial_ID+"=?", new String[]{demol3SerialId} );

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                RetailerDetailsPojo retailerDetailsPojo=new RetailerDetailsPojo();
                String firmName = cursor.getString(cursor.getColumnIndex(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_FIRM_NAME));
                String propName=  cursor.getString(cursor.getColumnIndex(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_PROPRIETOR_NAME));
                String retailerMobile= cursor.getString(cursor.getColumnIndex(RetailerDetailsPojo.RETAILERDETAILS_COLUMN_RETAILER_MOBILE));
                //   Toast.makeText(IndividualFarmerContactActivity.getInstance(), "FIRM NAME "+farmerName, Toast.LENGTH_SHORT).show();
                retailerDetailsPojo.setFirmName(firmName);
                retailerDetailsPojo.setPropName(propName);
                retailerDetailsPojo.setRetailerMobile(retailerMobile);


                list.add(retailerDetailsPojo);


            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();


        return list;
    }

    public List<String> getProductDetailsForDEMOL3Entries(String demol3SerialId) {
        List<String> list=new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +ProductsDetailsPojo.PRODUCTDETAILS_TABLE_NAME +
                " where "+ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PROMO_DEMOL3Serial_ID+"=?", new String[]{demol3SerialId} );

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(cursor.getColumnIndex(ProductsDetailsPojo.PRODUCTDETAILS_COLUMN_PRODUCT_NAME));



                list.add(productName);


            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();

        return list;
    }

    public List<byte[]> getAttachmentsForDEMOL3Entries(String demol3SerialId) {
        List<byte[]> list=new ArrayList<byte[]>();
        byte[] attachment=null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +AttachmentsPojo.ATTACHMENTS_TABLE_NAME +
                " where "+AttachmentsPojo.ATTACHMENTS_COLUMN_PROMO_DEMOL3Serial_ID+"=?", new String[]{demol3SerialId} );

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                try {
                    attachment = cursor.getBlob(cursor.getColumnIndex(AttachmentsPojo.ATTACHMENTS_COLUMN_ATTACHMENT_FILE));

                    if(attachment!=null)
                        list.add(attachment);
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }




            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();
       // db.close();

        return list;
    }

    public List<FarmerDetailsPojo> getDemoL3FarmerDetails(String demol3SerialId) {
        List<FarmerDetailsPojo> list=new ArrayList<FarmerDetailsPojo>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +FarmerDetailsPojo.FARMERDETAILS_TABLE_NAME +
                " where "+FarmerDetailsPojo.FARMERDETAILS_COLUMN_PROMO_DEMOL3Serial_ID+"=?", new String[]{demol3SerialId} );

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String farmerName = cursor.getString(cursor.getColumnIndex(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_NAME));
                String farmerMobile=cursor.getString(cursor.getColumnIndex(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_MOBILE));
                String farmerLand=cursor.getString(cursor.getColumnIndex(FarmerDetailsPojo.FARMERDETAILS_COLUMN_FARMER_LAND));
                //  Toast.makeText(IndividualFarmerContactActivity.getInstance(), "FARMER NAME "+farmerName, Toast.LENGTH_SHORT).show();

                FarmerDetailsPojo farmerDetailsPojo=new FarmerDetailsPojo();
                farmerDetailsPojo.setFarmerName(farmerName);
                farmerDetailsPojo.setFarmerMobile(farmerMobile);
                farmerDetailsPojo.setFarmerLand(farmerLand);

                list.add(farmerDetailsPojo);


            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





        cursor.close();
        //db.close();


        return list;
    }

    public void updateMobileFlagForDEMOL3Entries(ArrayList<String> uploadedIds) {

        SQLiteDatabase db=getWritableDatabase();

        for (int i=0;i<uploadedIds.size();i++)
        {
            String id=(uploadedIds.get(i)).toString();
            ContentValues cv = new ContentValues();
            cv.put(DemoL3Pojo.DEMOL3_COLUMN_UPLOAD_FLAG,"Yes");
            db.update(DemoL3Pojo.DEMOL3_TABLE_NAME, cv,DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID+" = ?", new String[]{id});
        }

      //  db.close();

    }

    public void updateDemoL3_PERMANENT_ID(String tempDemoL3Id, String permanentId) {
        SQLiteDatabase db=getWritableDatabase();

      //  Toast.makeText(DemoL3Activity.getInstance(), "PERMANENT ID is "+permanentId, Toast.LENGTH_SHORT).show();

        ContentValues cv = new ContentValues();
        cv.put(DemoL3Pojo.DEMOL3_COLUMN_PERMANENT_DEMOL3_SERIAL_ID, permanentId);
        db.update(DemoL3Pojo.DEMOL3_TABLE_NAME, cv,DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID+" = ?", new String[]{tempDemoL3Id});


        //db.close();
    }

    public void deleteAllEntriesOfFAMaster() {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ FaMasterPojo.FAMASTER_TABLE_NAME);
      //  db.close();

    }

    public void deleteAllEntriesOfVillages() {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ VillagesPojo.VILLAGES_TABLE_NAME);

     //   db.close();

    }
}


